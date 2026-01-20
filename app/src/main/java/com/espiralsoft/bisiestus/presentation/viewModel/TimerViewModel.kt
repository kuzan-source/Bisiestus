package com.espiralsoft.bisiestus.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.Year
import com.espiralsoft.bisiestus.domain.usecase.GetCurrentDateUseCase
import com.espiralsoft.bisiestus.presentation.states.TimerState
import com.espiralsoft.bisiestus.presentation.states.TimerUnit

class TimerViewModel(
    private val currentDate: GetCurrentDateUseCase = GetCurrentDateUseCase()
) : ViewModel()
{
    private val _state: MutableStateFlow<TimerState> = MutableStateFlow(TimerState(emptyList()))
    val state: StateFlow<TimerState> = _state.asStateFlow()

    init {
        startCountdown()
    }

    private fun startCountdown() {

        viewModelScope.launch {
            var currentDateTime: LocalDateTime = currentDate.invoke()
            val target: LocalDateTime = nextLeapYearUseCase(currentDateTime)

            while (true) {

                val duration: Duration = Duration.between(currentDateTime, target)

                if (!duration.isNegative) {
                    _state.value = _state.value.copy(
                        units = buildUnits(duration)
                    )
                } else {
                    _state.value = state.value.copy()
                    break
                }
                delay(1000)
                currentDateTime = currentDate.invoke()
            }
        }
    }

    fun buildUnits(duration: Duration): List<TimerUnit> {
        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60

        val units = mutableListOf<TimerUnit>()

        if (days > 0) {
            units += TimerUnit(days.toString(), "DÍAS")
        }
        if (days > 0 || hours > 0) {
            units += TimerUnit("%02d".format(hours), "HORAS")
        }
        if (days > 0 || hours > 0 || minutes > 0) {
            units += TimerUnit("%02d".format(minutes), "MIN")
        }

        units += TimerUnit("%02d".format(seconds), "SEG")

        return units
    }

    private fun nextLeapYearUseCase(currentDate: LocalDateTime): LocalDateTime {
        var year: Int = (currentDate.year +1)
        while (!Year.isLeap(year.toLong())) {
            year++
        }
        return LocalDateTime.of(year, 1, 1, 0, 0)
    }

    private fun validateYear(currentDate: LocalDateTime): Boolean {
        return Year.isLeap(currentDate.year.toLong())
    }

}
