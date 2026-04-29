package ru.itis.taskmanager.network.pojo

import com.google.gson.annotations.SerializedName

data class UpdateTaskRequestDto(
    val title: String? = null,
    val description: String? = null,
    @SerializedName("task_type")
    val taskType: String? = null,
    val status: String? = null,
    @SerializedName("due_at")
    val dueAt: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    @SerializedName("radius_meters")
    val radiusMeters: Int? = null,
)
