package com.espiralsoft.bisiestus.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun TimeSeparator() {
    Text(
        text = ":",
        fontFamily = FontFamily.Monospace,
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}
