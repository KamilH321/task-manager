package ru.itis.taskmanager.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.taskmanager.domain.auth.usecase.GetUserUseCase
import ru.itis.taskmanager.domain.auth.usecase.LoginUseCase
import ru.itis.taskmanager.domain.auth.usecase.RegisterUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

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
            }.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        registeredUser = user,
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

    fun onLoginClick() {
        val state = _uiState.value
        if (state.username.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Введите username и password") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }

            runCatching {
                loginUseCase(state.username, state.password)
                getUserUseCase()
            }.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        currentUser = user,
                        successMessage = "Авторизация успешна",
                        errorMessage = null
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Ошибка авторизации"
                    )
                }
            }
        }
    }

}