package ru.itis.taskmanager.tasks.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.itis.taskmanager.designsystem.components.cards.TaskManagerCard
import ru.itis.taskmanager.designsystem.theme.Info
import ru.itis.taskmanager.designsystem.theme.Success
import ru.itis.taskmanager.designsystem.theme.TaskManagerSpacing
import ru.itis.taskmanager.designsystem.theme.TaskManagerThemeExt
import ru.itis.taskmanager.designsystem.theme.Warning
import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.model.task.TaskStatus

@Composable
fun TaskListScreen(
    state: TaskListUiState,
    onTaskClick: (Int) -> Unit,
    onAddTaskClick: () -> Unit,
    onDeleteTask: (Int) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTaskClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Default.Add, contentDescription = "Создать задачу")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (state.isLoading && state.tasks.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(TaskManagerSpacing.md),
                verticalArrangement = Arrangement.spacedBy(TaskManagerSpacing.sm)
            ) {
                items(state.tasks) { task ->
                    TaskListItemRow(
                        task = task,
                        onClick = { onTaskClick(task.id) },
                        onDelete = { onDeleteTask(task.id) }
                    )
                }
            }

            state.errorMessage?.let { errorRes ->
                Text(
                    text = stringResource(errorRes),
                    color = MaterialTheme.colorScheme.error,
                    style = TaskManagerThemeExt.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(TaskManagerSpacing.md)
                )
            }
        }
    }
}

@Composable
private fun TaskListItemRow(
    task: Task,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    TaskManagerCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(TaskManagerSpacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = TaskManagerThemeExt.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(TaskManagerSpacing.xs))

                val statusColor = when (task.status) {
                    TaskStatus.PENDING -> Warning
                    TaskStatus.IN_PROGRESS -> Info
                    TaskStatus.DONE -> Success
                    TaskStatus.CANCELLED -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                }

                Text(
                    text = task.status.name,
                    style = TaskManagerThemeExt.typography.bodySmall,
                    color = statusColor
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить задачу",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}