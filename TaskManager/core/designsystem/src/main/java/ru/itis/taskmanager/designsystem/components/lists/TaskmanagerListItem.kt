package ru.itis.taskmanager.designsystem.components.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.itis.taskmanager.designsystem.theme.TaskManagerSpacing

@Composable
fun TaskManagerListItem(
    title: String,
    subtitle: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(TaskManagerSpacing.md)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        subtitle?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}