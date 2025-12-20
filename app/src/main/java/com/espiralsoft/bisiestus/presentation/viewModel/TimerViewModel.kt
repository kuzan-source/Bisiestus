package com.espiralsoft.bisiestus.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espiralsoft.bisiestus.domain.usecase.GetCurrentDateUseCase
import com.espiralsoft.bisiestus.presentation.states.TimerStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerViewModel(
    private val getCurrentDateUseCase: GetCurrentDateUseCase = GetCurrentDateUseCase()
) : ViewModel()
{
    private val _state: MutableStateFlow<TimerStatus> = MutableStateFlow(TimerStatus())
    val state = _state.asStateFlow()

    fun showCurrentTime()
    {
        viewModelScope.launch {
            _state.value = TimerStatus(getCurrentDateUseCase())
        }
    }

}
