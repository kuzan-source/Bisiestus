package com.espiralsoft.bisiestus.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AnimatedNumber(value: String) {
    AnimatedContent(
        targetState = value,
        transitionSpec = {
            slideInVertically { it } + fadeIn() togetherWith
                    slideOutVertically { -it } + fadeOut()
        },
        label = "numberAnimation"
    ) { target ->
        Text(
            text = target,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
