package com.espiralsoft.bisiestus.presentation.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1500)
    )

    val topColor = lerp(
        Color(0xFF0B1C2D), // Azul petróleo oscuro
        Color(0xFF1E3A5F), // Azul más luminoso
        animatedProgress
    )

    val bottomColor = lerp(
        Color(0xFF1A1028), // Violeta profundo
        Color(0xFF3A1F5D), // Violeta más brillante
        animatedProgress
    )

    val finalTop = if (isFeb29) Color(0xFF2C3E80) else topColor
    val finalBottom = if (isFeb29) Color(0xFF5A2E91) else bottomColor

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(finalTop, finalBottom)
                )
            )
    ) {
        content()
    }

}
