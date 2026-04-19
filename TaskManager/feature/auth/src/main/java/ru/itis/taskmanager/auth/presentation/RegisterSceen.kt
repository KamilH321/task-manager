package ru.itis.taskmanager.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerButton
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerOutlinedButton
import ru.itis.taskmanager.designsystem.components.inputs.TaskManagerTextField

@Composable
fun RegisterRouteScreen(
    factory: ViewModelProvider.Factory,
    onBackToAuth: () -> Unit
) {
    val viewModel: RegisterViewModel = viewModel(factory = factory)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.successMessage) {
        if (state.successMessage != null) {
            onBackToAuth()
        }
    }

    RegisterScreen(
        state = state,
        onUsernameChange = viewModel::onUsernameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRegisterClick = viewModel::onRegisterClick,
        onBackClick = onBackToAuth
    )
}

@Composable
fun RegisterScreen(
    state: RegisterUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxWidth()
                .widthIn(max = 420.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Регистрация",
                style = MaterialTheme.typography.headlineMedium
            )

            TaskManagerTextField(
                value = state.username,
                onValueChange = onUsernameChange,
                label = "Username",
                singleLine = true
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    errorCursorColor = MaterialTheme.colorScheme.error
                )
            )

            TaskManagerButton(
                text = "Создать аккаунт",
                onClick = onRegisterClick,
                isLoading = state.isLoading
            )

            TaskManagerOutlinedButton(
                text = "Назад",
                onClick = onBackClick,
                isLoading = state.isLoading
            )

            state.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            state.successMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}