package ru.itis.taskmanager.domain.auth.model

data class AuthToken(
    val accessToken: String,
    val tokenType: String
)
