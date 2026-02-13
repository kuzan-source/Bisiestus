package com.espiralsoft.bisiestus.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
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
    private val currentDate: GetCurrentDateUseCase = GetCurrentDateUseCase(),
    private val resolveCountdownTarget: ResolveCountdownTarget = ResolveCountdownTarget()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountdownUiState())
    val uiState: StateFlow<CountdownUiState> = _uiState

    init {
        start()
    }

    private fun start() {
        when (val target: CountdownTarget = resolveCountdownTarget.execute(currentDate())) {

            is CountdownTarget.IsFeb29 -> {
                _uiState.value = CountdownUiState(isFeb29 = true)
            }

            is CountdownTarget.Target -> {
                startCountdown(target.dateTime)
            }
        }
    }

    private fun startCountdown(target: LocalDateTime) {
        viewModelScope.launch {
            while (isActive) {
                val duration: Duration = Duration.between(currentDate(), target)

                if (duration.isNegative || duration.isZero) {
                    _uiState.value = CountdownUiState()
                    cancel()
                    return@launch
                }

                val totalSeconds:Long = duration.seconds

                val weeks: Long = totalSeconds / 604_800
                val days: Long = (totalSeconds % 604_800) / 86_400
                val hours: Long = (totalSeconds % 86_400) / 3_600
                val minutes: Long = (totalSeconds % 3_600) / 60
                val seconds: Long = totalSeconds % 60

                _uiState.value = CountdownUiState(
                    weeks = weeks,
                    days = days,
                    hours = hours,
                    minutes = minutes,
                    seconds = seconds
                )

                delay(1_000)
            }
        }
    }
}
