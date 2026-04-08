package ru.itis.taskmanager.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerButton
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerOutlinedButton
import ru.itis.taskmanager.designsystem.components.cards.TaskManagerCard
import ru.itis.taskmanager.designsystem.components.inputs.TaskManagerTextField
import ru.itis.taskmanager.domain.auth.model.User

@Composable
fun AuthRote(
    factory: ViewModelProvider.Factory,
    onAuthenticated: (User) -> Unit = {}
) {

    val viewModel: AuthViewModel = viewModel(factory = factory)
    val state by viewModel.uiState.collectAsState()

    AuthScreen(
        state = state,
        onUsernameChange = viewModel::onUsernameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onRegisterClick = viewModel::onRegisterClick
    )

    state.currentUser?.let(onAuthenticated)
}

@Composable
fun AuthScreen(
    state: AuthUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Scaffold {
            paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxWidth()
                .widthIn(max = 420.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Вход / Регистрация",
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
                text = "Войти",
                onClick = onLoginClick,
                isLoading = state.isLoading
            )

            TaskManagerOutlinedButton(
                text = "Зарегистрироваться",
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

            state.successMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            state.currentUser?.let { user ->
                TaskManagerCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Текущий пользователь", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("id: ${user.id}")
                        Text("username: ${user.username}")
                        Text("createdAt: ${user.createdAt}")
                    }
                }
            }

            state.registeredUser?.let { user ->
                TaskManagerCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Зарегистрирован", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("id: ${user.id}")
                        Text("username: ${user.username}")
                    }
                }
            }
        }
    }
}