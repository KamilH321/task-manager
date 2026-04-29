package ru.itis.taskmanager.network.pojo

import com.google.gson.annotations.SerializedName

data class TaskResponseDto(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
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
    val radiusMeters: Int? = null,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
