package ru.itis.taskmanager.domain.auth.model.task

import kotlin.time.Instant

data class CreateTaskParams(
    val title: String,
    val description: String?,
    val type: TaskType,
    val status: TaskStatus = TaskStatus.PENDING,
    val dueAt: Instant? = null
)

sealed interface TaskType {
    data object Common: TaskType
    data class Geo(
        val latitude: Double,
        val longitude: Double,
        val radiusMeters: Int = 100
    ): TaskType
}

enum class TaskStatus { PENDING, IN_PROGRESS, DONE, CANCELLED }

