package ru.itis.taskmanager.tasks.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.taskmanager.domain.auth.model.task.CreateTaskParams
import ru.itis.taskmanager.domain.auth.model.task.TaskStatus
import ru.itis.taskmanager.domain.auth.model.task.TaskType
import ru.itis.taskmanager.domain.auth.model.task.UpdateTaskParams
import ru.itis.taskmanager.domain.auth.usecase.task.CreateTaskUseCase
import ru.itis.taskmanager.domain.auth.usecase.task.GetTaskUseCase
import ru.itis.taskmanager.domain.auth.usecase.task.UpdateTaskUseCase
import ru.itis.taskmanager.tasks.R
import javax.inject.Inject

class TaskFormViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val taskId: Int? = savedStateHandle.get<Int>("taskId")

    private val _uiState = MutableStateFlow(TaskFormUiState())
    val uiState: StateFlow<TaskFormUiState> = _uiState.asStateFlow()

    init {
        taskId?.let { loadTask(it) }
    }

    private fun loadTask(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching { getTaskUseCase(id) }
                .onSuccess { task ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        title = task.title,
                        description = task.description ?: "",
                        status = task.status,
                        initialTaskLoaded = true
                    ) }
                }
        }
    }

    fun onTitleChange(newTitle: String) = _uiState.update { it.copy(title = newTitle) }
    fun onDescriptionChange(newDesc: String) = _uiState.update { it.copy(description = newDesc) }

    fun onStatusChange(newStatus: TaskStatus) {
        _uiState.update { it.copy(status = newStatus) }
    }

    fun saveTask() {
        viewModelScope.launch {
            val state = _uiState.value
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                if (taskId == null) {
                    createTaskUseCase(
                        CreateTaskParams(
                            title = state.title,
                            description = state.description,
                            type = TaskType.Common
                        )
                    )
                } else {
                    updateTaskUseCase(taskId, UpdateTaskParams(
                        title = state.title,
                        description = state.description,
                        status = state.status
                    )
                    )
                }
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false, isSaved = true) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false, errorMessage = R.string.test) }
            }
        }
    }
}