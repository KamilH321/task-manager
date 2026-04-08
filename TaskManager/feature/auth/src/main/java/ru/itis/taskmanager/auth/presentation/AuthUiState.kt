package ru.itis.taskmanager.auth.presentation

import ru.itis.taskmanager.domain.auth.model.User

data class AuthUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val currentUser: User? = null,
    val registeredUser: User? = null
)
