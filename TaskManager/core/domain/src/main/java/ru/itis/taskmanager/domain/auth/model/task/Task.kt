package ru.itis.taskmanager.domain.auth.model.task

data class Task(
    val id: Int,
    val userId: Int,
    val title: String,
    val description: String?,
    val taskType: String = "common",
    val status: String,
    val dueAt: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val radiusMeters: Int? = null,
    val createdAt: String,
    val updatedAt: String
)
