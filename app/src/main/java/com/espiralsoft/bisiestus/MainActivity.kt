package com.espiralsoft.bisiestus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.espiralsoft.bisiestus.presentation.ui.screens.TimerScreen
import com.espiralsoft.bisiestus.presentation.ui.theme.BisiestusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BisiestusTheme {
                TimerScreen()
            }
        }
    }
}
