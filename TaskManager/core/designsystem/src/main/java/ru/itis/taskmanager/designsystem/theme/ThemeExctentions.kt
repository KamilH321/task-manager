package ru.itis.taskmanager.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

object TaskManagerThemeExt {

    val colors
        @Composable
        get() = MaterialTheme.colorScheme

    val typography
        @Composable
        get() = MaterialTheme.typography

    val shapes
        @Composable
        get() = MaterialTheme.shapes
}