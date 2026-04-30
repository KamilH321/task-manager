package ru.itis.taskmanager.data.auth.mapper

import ru.itis.taskmanager.domain.auth.model.task.CreateTaskParams
import ru.itis.taskmanager.domain.auth.model.task.GeoLocation
import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.model.task.TaskStatus
import ru.itis.taskmanager.domain.auth.model.task.TaskType
import ru.itis.taskmanager.domain.auth.model.task.UpdateTaskParams
import ru.itis.taskmanager.network.pojo.CreateTaskRequestDto
import ru.itis.taskmanager.network.pojo.TaskResponseDto
import ru.itis.taskmanager.network.pojo.UpdateTaskRequestDto
import kotlin.time.Instant

fun CreateTaskParams.toRequest(): CreateTaskRequestDto {

    val (lat, lng, radius) = when (type) {
        is TaskType.Common -> Triple(null, null, null)
        is TaskType.Geo -> Triple((type as TaskType.Geo).latitude, (type as TaskType.Geo).longitude, (type as TaskType.Geo).radiusMeters)
    }

    return CreateTaskRequestDto(
        title = title,
        description = description,
        taskType = type.toApiString(),
        status = status.name,
        dueAt = dueAt?.toString(),
        latitude = lat,
        longitude = lng,
        radiusMeters = radius
    )
}

fun UpdateTaskParams.toRequest(): UpdateTaskRequestDto {

    val (lat, lng, radius) = location?.let { Triple(it.latitude, it.longitude, it.radiusMeters) } ?: Triple(null, null, null)

    return UpdateTaskRequestDto(
        title = title,
        description = description,
        taskType = type?.toApiString(),
        status = status?.name,
        dueAt = dueAt?.toString(),
        latitude = lat,
        longitude = lng,
        radiusMeters = radius
    )
}

fun TaskResponseDto.toDomain(): Task {

    val type = when (taskType.lowercase()) {
            "common" -> TaskType.Common
            "geo" -> TaskType.Geo(
                    latitude = latitude!!,
                    longitude = longitude!!,
                    radiusMeters = radiusMeters ?: 100
            )
            else -> error("Unknown task type: $taskType")
    }

    return Task(
        id = id,
        userId = userId,
        title = title,
        description = description,
        type = type,
        status = TaskStatus.valueOf(status.uppercase()),
        dueAt = dueAt?.let { Instant.parse(it) },
        location = if (type is TaskType.Geo) GeoLocation(type.latitude, type.longitude, type.radiusMeters) else null,
        createdAt = Instant.parse(createdAt),
        updatedAt = Instant.parse(updatedAt)
    )
}

private fun TaskType.toApiString() = when (this) {
    is TaskType.Common -> "common"
    is TaskType.Geo -> "geo"
}





