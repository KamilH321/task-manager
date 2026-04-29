package ru.itis.taskmanager.domain.auth.model.auth

data class AuthToken(
    val accessToken: String,
    val tokenType: String
)
