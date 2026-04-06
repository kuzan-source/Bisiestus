package com.espiralsoft.bisiestus.presentation.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

/**
 * Un componente que proporciona un fondo degradado dinámico y
 * animado basado en el progreso y el estado del año bisiesto.
 *
 * El fondo utiliza un degradado vertical que interpola entre diferentes colores del tema
 * Material Design a medida que cambia el valor de [progress]. Si [isFeb29] es verdadero,
 * aplica una combinación de colores específica para el día bisiesto.
 *
 * @param progress Valor decimal (entre 0 y 1) que se utiliza para animar la transición entre colores.
 * @param isFeb29 Un indicador booleano que señala si hoy es 29 de febrero.
 * @param content El contenido de la interfaz de usuario, se mostrará sobre el fondo animado.
 */
@Composable
fun ChangingBackground(
    progress: Float,
    isFeb29: Boolean,
    content: @Composable () -> Unit
) {

    val animatedProgress: Float by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000)
    )

    val topColor: Color = lerp(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        animatedProgress
    )

    val bottomColor: Color = lerp(
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        animatedProgress
    )

    val finalTop: Color = if (isFeb29) MaterialTheme.colorScheme.tertiary else topColor
    val finalBottom: Color = if (isFeb29) MaterialTheme.colorScheme.secondary else bottomColor

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0.0f to finalTop,
                    0.50f to MaterialTheme.colorScheme.background,
                    0.55f to MaterialTheme.colorScheme.background,
                    1.0f to finalBottom
                )
            )
    ) {
        content()
    }

}
