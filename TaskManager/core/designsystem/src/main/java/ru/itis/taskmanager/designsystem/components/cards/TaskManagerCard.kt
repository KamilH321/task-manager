package ru.itis.taskmanager.designsystem.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.itis.taskmanager.designsystem.theme.TaskManagerThemeExt

@Composable
fun TaskManagerCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        content()
    }
}

@Composable
fun TaskManagerStyledCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val colors = TaskManagerThemeExt.colors

    Box(
        modifier = modifier
            .size(width = 395.dp, height = 233.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(colors.surfaceVariant)
            .border(
                width = 2.dp,
                color = colors.onSurfaceVariant.copy(alpha = 0.4f),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        content()
    }
}