package ru.itis.taskmanager.network.pojo

import com.google.gson.annotations.SerializedName

data class CreateTaskRequestDto(
    val title: String,
    val description: String?,
    @SerializedName("task_type")
    val taskType: String = "common",
    val status: String,
    @SerializedName("due_at")
    val dueAt: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    @SerializedName("radius_meters")
    val radiusMeters: Int? = null
)
