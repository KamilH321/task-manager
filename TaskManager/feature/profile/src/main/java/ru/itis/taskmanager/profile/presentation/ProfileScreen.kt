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
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
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
            Text(
                text = stringResource(R.string.header_text),
                style = MaterialTheme.typography.headlineMedium
            )

            if (state.isLoading) {
                Text(
                    text = stringResource(R.string.loading_text),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            state.user?.let { user ->
                TaskManagerCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(stringResource(R.string.user_label), style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(stringResource(R.string.id_label, user.id))
                        Text(stringResource(R.string.username_label, user.username))
                        Text(stringResource(R.string.created_at_label, user.createdAt))
                    }
                }
            }

            state.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            TaskManagerButton(
                text = stringResource(R.string.loading_text),
                onClick = onLogoutClick
            )
        }
    }
}