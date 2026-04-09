package ru.itis.taskmanager.network.pojo

import com.google.gson.annotations.SerializedName

data class AuthTokenDto(
    @SerializedName(value = "access_token")
    val accessToken: String,
    @SerializedName(value = "token_type")
    val tokenType: String,
)