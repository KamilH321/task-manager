package ru.itis.taskmanager.auth.presentation

data class AuthUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val authenticated: Boolean = false
)
