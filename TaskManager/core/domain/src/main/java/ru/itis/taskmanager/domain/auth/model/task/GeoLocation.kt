package ru.itis.taskmanager.domain.auth.model.task

data class GeoLocation(
    val latitude: Double,
    val longitude: Double,
    val radiusMeters: Int = 100
)
