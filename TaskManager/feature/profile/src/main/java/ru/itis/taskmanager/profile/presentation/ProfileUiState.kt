package ru.itis.taskmanager.profile.presentation

import ru.itis.taskmanager.domain.auth.model.auth.User

data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)
