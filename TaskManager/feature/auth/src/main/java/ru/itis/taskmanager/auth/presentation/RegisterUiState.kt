package ru.itis.taskmanager.auth.presentation

data class RegisterUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
