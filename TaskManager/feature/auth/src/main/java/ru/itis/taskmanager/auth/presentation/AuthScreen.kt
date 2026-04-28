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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.taskmanager.auth.R
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerButton
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerOutlinedButton
import ru.itis.taskmanager.designsystem.components.inputs.TaskManagerTextField

@Composable
fun AuthRouteScreen(
    factory: ViewModelProvider.Factory,
    onNavigateToRegister: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val viewModel: AuthViewModel = viewModel(factory = factory)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.authenticated) {
        if (state.authenticated) {
            onAuthenticated()
            viewModel.consumeAuthEvent()
        }
    }

    AuthScreen(
        state = state,
        onUsernameChange = viewModel::onUsernameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onRegisterClick = onNavigateToRegister
    )
}

@Composable
fun AuthScreen(
    state: AuthUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
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
                text = stringResource(R.string.login_text),
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = stringResource(R.string.greeting_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            TaskManagerTextField(
                value = state.username,
                onValueChange = onUsernameChange,
                label = stringResource(R.string.username_label),
                singleLine = true
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                label = { Text(stringResource(R.string.password_label)) },
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
                text = stringResource(R.string.login_button_text),
                onClick = onLoginClick,
                isLoading = state.isLoading
            )

            TaskManagerOutlinedButton(
                text = stringResource(R.string.resister_button_text),
                onClick = onRegisterClick,
                isLoading = state.isLoading
            )

            state.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}