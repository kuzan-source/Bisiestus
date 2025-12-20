package com.espiralsoft.bisiestus.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espiralsoft.bisiestus.domain.usecase.GetCurrentDateUseCase
import com.espiralsoft.bisiestus.presentation.states.TimerStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.Year

class TimerViewModel(
    private val currentDate: GetCurrentDateUseCase = GetCurrentDateUseCase()
) : ViewModel()
{
    private val _state: MutableStateFlow<TimerStatus> = MutableStateFlow(TimerStatus())
    val state = _state.asStateFlow()

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
                    _state.value = _state.value.copy(timeNow = format(duration))
                } else {
                    _state.value = state.value.copy(timeNow = "Ya es año bisiesto")
                    break
                }
                delay(1000)
                currentDateTime = currentDate.invoke()
            }
        }
    }

    private fun format(d: Duration): String {
        val days = d.toDays()
        val hours = d.toHours() % 24
        val minutes = d.toMinutes() % 60
        val seconds = d.seconds % 60

        return "%d días %02d:%02d:%02d".format(days, hours, minutes, seconds)
    }

    private fun nextLeapYearUseCase(currentDate: LocalDateTime): LocalDateTime {
        var year: Int = (currentDate.year +1)
        while (!Year.isLeap(year.toLong())) {
            year++
        }
        return LocalDateTime.of(year, 1, 1, 0, 0)
    }

    private fun validateYearUseCase(currentDate: LocalDateTime): Boolean {
        val currentYear: Int = currentDate.year
        return Year.isLeap(currentYear.toLong())
    }

}
