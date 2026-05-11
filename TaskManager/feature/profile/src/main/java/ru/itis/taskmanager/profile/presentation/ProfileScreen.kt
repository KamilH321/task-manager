package ru.itis.taskmanager.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerButton
import ru.itis.taskmanager.designsystem.components.cards.TaskManagerCard
import ru.itis.taskmanager.domain.auth.model.auth.User
import ru.itis.taskmanager.profile.R

@Composable
fun ProfileRouteScreen(
    factory: ViewModelProvider.Factory,
    onLogoutClick: () -> Unit
) {
    val viewModel: ProfileViewModel = viewModel(factory = factory)
    val state by viewModel.uiState.collectAsState()

    ProfileScreen(
        state = state,
        onLogoutClick = {
            viewModel.logout()
            onLogoutClick()
        }
    )
}

@Composable
fun ProfileScreen(
    state: ProfileUiState,
    onLogoutClick: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxWidth()
                .widthIn(max = 480.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileHeader()

            if (state.isLoading) {
                ProfileLoadingText()
            }

            state.user?.let { user ->
                ProfileUserCard(user = user)
            }

            ProfileErrorMessage(errorResId = state.errorMessage)

            ProfileLogoutButton(onLogoutClick = onLogoutClick)
        }
    }
}

@Composable
private fun ProfileHeader() {
    Text(
        text = stringResource(R.string.header_text),
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
private fun ProfileLoadingText() {
    Text(
        text = stringResource(R.string.loading_text),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun ProfileUserCard(user: User) {
    TaskManagerCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.user_label),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.id_label, user.id))
            Text(text = stringResource(R.string.username_label_, user.username))
            Text(text = stringResource(R.string.created_at_label, user.createdAt))
        }
    }
}

@Composable
private fun ProfileErrorMessage(errorResId: Int?) {
    errorResId?.let { resId ->
        Text(
            text = stringResource(resId),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ProfileLogoutButton(onLogoutClick: () -> Unit) {
    TaskManagerButton(
        text = stringResource(R.string.logout_button_text),
        onClick = onLogoutClick
    )
}