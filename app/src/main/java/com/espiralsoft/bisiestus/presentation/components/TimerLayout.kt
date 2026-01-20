package com.espiralsoft.bisiestus.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.espiralsoft.bisiestus.presentation.states.TimerUnit

@Composable
fun TimerLayout(
    units: List<TimerUnit>
) {
    val orientacionScreen: Int = LocalConfiguration.current.orientation

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (orientacionScreen == Configuration.ORIENTATION_LANDSCAPE) {
            HorizontalTimerContent(units)
        } else {
            VerticalTimerContent(units)
        }
    }
}
