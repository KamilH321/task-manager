package ru.itis.taskmanager.domain.auth.model.task

import kotlin.time.Instant

data class Task(
    val id: Int,
    val userId: Int,
    val title: String,
    val description: String?,
    val type: TaskType,
    val status: TaskStatus,
    val dueAt: Instant?,
    val location: GeoLocation?,
    val createdAt: Instant,
    val updatedAt: Instant
)
