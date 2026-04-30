package ru.itis.taskmanager.domain.auth.model.task

import kotlin.time.Instant

data class UpdateTaskParams(
    val title: String? = null,
    val description: String? = null,
    val type: TaskType? = null,
    val status: TaskStatus? = null,
    val dueAt: Instant? = null,
    val location: GeoLocation? = null
)
