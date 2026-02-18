package com.espiralsoft.bisiestus.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.espiralsoft.bisiestus.presentation.ui.components.ElegantBackground
import com.espiralsoft.bisiestus.presentation.ui.components.Text29FebLayout
import com.espiralsoft.bisiestus.presentation.ui.components.TimerLayout
import com.espiralsoft.bisiestus.presentation.viewModel.CountdownViewModel

@Composable
fun TimerScreen(
    viewModel: CountdownViewModel = CountdownViewModel(),
) {

    val state by viewModel.uiState.collectAsState()

    val progress: Float = state.progressionColor

    ElegantBackground(
        progress = progress,
        isFeb29 = state.isFeb29
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Próximo año bisiesto",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (state.isFeb29) {
                Text29FebLayout()
            } else {
                TimerLayout(units = state)
            }
        }
    }
}
