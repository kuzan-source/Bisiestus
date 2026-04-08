package com.espiralsoft.bisiestus.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import com.espiralsoft.bisiestus.domain.model.CountdownTarget
import com.espiralsoft.bisiestus.domain.usecase.GetCurrentDateUseCase
import com.espiralsoft.bisiestus.domain.usecase.ResolveCountdownTarget
import com.espiralsoft.bisiestus.presentation.states.CountdownUiState

class CountdownViewModel(
    private val getCurrentDate: GetCurrentDateUseCase = GetCurrentDateUseCase(),
    private val resolveCountdownTarget: ResolveCountdownTarget = ResolveCountdownTarget()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountdownUiState())
    val uiState: StateFlow<CountdownUiState> = _uiState
    val maxSeconds: Int = 1_431 * 86_400 // Numero de dias * segundos en un dia

    init {
        observeTime()
    }

    private fun observeTime() {
        viewModelScope.launch {
            while (isActive) {

                val now: LocalDateTime = getCurrentDate()
                when (val target: CountdownTarget = resolveCountdownTarget.execute(now)) {

                    is CountdownTarget.IsFeb29 -> {
                        _uiState.value = CountdownUiState(isFeb29 = true)
                    }

                    is CountdownTarget.Target -> {
                        val duration: Duration = Duration.between(now, target.dateTime)

                        if (duration.isNegative || duration.isZero) {
                            _uiState.value = CountdownUiState()
                        } else {
                            _uiState.value = duration.toUiState()
                        }
                    }
                }

                delay(1_000)
            }
        }
    }

    private fun Duration.toUiState(): CountdownUiState {
        val totalSeconds: Int = seconds.toInt()

        val weeks: Int = totalSeconds / 604_800
        val remainingAfterWeeks: Int = totalSeconds % 604_800

        val days: Int = remainingAfterWeeks / 86_400
        val remainingAfterDays: Int = remainingAfterWeeks % 86_400

        val hours: Int = remainingAfterDays / 3_600
        val remainingAfterHours: Int = remainingAfterDays % 3_600

        val minutes: Int = remainingAfterHours / 60
        val seconds: Int = remainingAfterHours % 60

        val progressionColor: Float = 1 - totalSeconds.toFloat() / maxSeconds.toFloat()

        return CountdownUiState(
            weeks = weeks,
            days = days,
            hours = hours,
            minutes = minutes,
            seconds = seconds,
            progressionColor = progressionColor
        )

    }
}
