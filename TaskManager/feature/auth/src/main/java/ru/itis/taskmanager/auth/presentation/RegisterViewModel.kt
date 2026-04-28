package ru.itis.taskmanager.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.taskmanager.domain.auth.usecase.RegisterUseCase
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.update { it.copy(username = value, errorMessage = null, successMessage = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = null, successMessage = null) }
    }

    fun onRegisterClick() {
        val state = _uiState.value
        if (state.username.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Введите username и password") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }

            runCatching {
                registerUseCase(state.username, state.password)
            }.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        successMessage = "Пользователь зарегистрирован",
                        errorMessage = null
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Ошибка регистрации"
                    )
                }
            }
        }
    }
}