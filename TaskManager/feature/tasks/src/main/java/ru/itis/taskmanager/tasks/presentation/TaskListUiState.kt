package ru.itis.taskmanager.tasks.presentation

import ru.itis.taskmanager.domain.auth.model.task.Task

data class TaskListUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val errorMessage: Int? = null
)
