package com.espiralsoft.bisiestus.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espiralsoft.bisiestus.domain.model.CountdownTarget
import com.espiralsoft.bisiestus.domain.usecase.ResolveCountdownTarget
import com.espiralsoft.bisiestus.presentation.states.CountdownUiState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime


class CountdownViewModel(
    private val resolveCountdownTarget: ResolveCountdownTarget = ResolveCountdownTarget()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountdownUiState())
    val uiState: StateFlow<CountdownUiState> = _uiState

    init {
        start()
    }

    private fun start() {
        val now = LocalDateTime.now()
        when (val target = resolveCountdownTarget.execute(now)) {

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
                val now = LocalDateTime.now()
                val duration = Duration.between(now, target)

                if (duration.isNegative || duration.isZero) {
                    _uiState.value = CountdownUiState()
                    cancel()
                    return@launch
                }

                val totalSeconds = duration.seconds

                val days = totalSeconds / 86_400
                val hours = (totalSeconds % 86_400) / 3_600
                val minutes = (totalSeconds % 3_600) / 60
                val seconds = totalSeconds % 60

                _uiState.value = CountdownUiState(
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
