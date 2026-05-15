package ru.itis.taskmanager.tasks.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer // Добавлено
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerButton // Добавлено
import ru.itis.taskmanager.designsystem.components.buttons.TaskManagerDropdownButton
import ru.itis.taskmanager.designsystem.components.inputs.TaskManagerTextField
import ru.itis.taskmanager.designsystem.theme.TaskManagerSpacing
import ru.itis.taskmanager.designsystem.theme.TaskManagerThemeExt
import ru.itis.taskmanager.domain.auth.model.task.TaskStatus


@Composable
fun TaskFormRouteScreen(
    factory: ViewModelProvider.Factory,
    onBack: () -> Unit
) {
    val viewModel: TaskFormViewModel = viewModel(factory = factory)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) onBack()
    }

    TaskFormScreen(
        state = state,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onStatusChange = viewModel::onStatusChange,
        onSaveClick = viewModel::saveTask,
        onBackClick = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    state: TaskFormUiState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onStatusChange: (TaskStatus) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (state.initialTaskLoaded) "Редактирование" else "Новая задача",
                        style = TaskManagerThemeExt.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(TaskManagerSpacing.lg)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(TaskManagerSpacing.md)
        ) {

            TaskManagerTextField(
                value = state.title,
                onValueChange = onTitleChange,
                label = "Название задачи",
                singleLine = true,
                isError = state.errorMessage != null && state.title.isBlank(),
                errorMessage = "Название не может быть пустым"
            )

            TaskManagerTextField(
                value = state.description,
                onValueChange = onDescriptionChange,
                label = "Описание",
                singleLine = false
            )

            if (state.initialTaskLoaded) {
                Text(
                    text = "Статус задачи",
                    style = TaskManagerThemeExt.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                TaskManagerDropdownButton(
                    text = state.status.name,
                    options = TaskStatus.entries.map { it.name },
                    onOptionSelected = { selectedName ->
                        onStatusChange(TaskStatus.valueOf(selectedName))
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            state.errorMessage?.let { errorRes ->
                Text(
                    text = stringResource(errorRes),
                    color = MaterialTheme.colorScheme.error,
                    style = TaskManagerThemeExt.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = TaskManagerSpacing.xs)
                )
            }

            // Фирменная кнопка сохранения со встроенным лоадером
            TaskManagerButton(
                text = if (state.initialTaskLoaded) "Обновить задачу" else "Создать задачу",
                onClick = onSaveClick,
                isLoading = state.isLoading,
                enabled = state.title.isNotBlank()
            )
        }
    }
}