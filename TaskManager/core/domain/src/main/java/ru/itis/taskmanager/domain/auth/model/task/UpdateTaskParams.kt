package ru.itis.taskmanager.domain.auth.model.task

data class UpdateTaskParams(
    val title: String? = null,
    val description: String? = null,
    val taskType: String? = null,
    val status: String? = null,
    val dueAt: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val radiusMeters: Int? = null,
)
