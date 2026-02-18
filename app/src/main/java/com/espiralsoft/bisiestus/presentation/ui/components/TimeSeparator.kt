package com.espiralsoft.bisiestus.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TimeSeparator() {
    Text(
        text = ":",
        style = MaterialTheme.typography.displayMedium,
        color = Color.White.copy(alpha = 0.9f)
    )
}
