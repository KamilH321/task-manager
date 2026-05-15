package ru.itis.taskmanager.tasks.presentation

import ru.itis.taskmanager.domain.auth.model.task.TaskStatus

data class TaskFormUiState(
    val isLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val status: TaskStatus = TaskStatus.PENDING,
    val isSaved: Boolean = false,
    val errorMessage: Int? = null,
    val initialTaskLoaded: Boolean = false
)
