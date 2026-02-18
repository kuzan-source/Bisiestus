package com.espiralsoft.bisiestus.presentation.viewModel

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
        val totalSeconds: Long = seconds

        val weeks: Long = totalSeconds / 604_800
        val remainingAfterWeeks: Long = totalSeconds % 604_800

        val days: Long = remainingAfterWeeks / 86_400
        val remainingAfterDays: Long = remainingAfterWeeks % 86_400

        val hours: Long = remainingAfterDays / 3_600
        val remainingAfterHours: Long = remainingAfterDays % 3_600

        val minutes: Long = remainingAfterHours / 60
        val seconds: Long = remainingAfterHours % 60

        return CountdownUiState(
            weeks = weeks,
            days = days,
            hours = hours,
            minutes = minutes,
            seconds = seconds
        )
    }
}
