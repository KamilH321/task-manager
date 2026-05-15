package ru.itis.taskmanager.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.taskmanager.domain.auth.usecase.task.DeleteTaskUseCase
import ru.itis.taskmanager.domain.auth.usecase.task.GetTasksListUseCase
import ru.itis.taskmanager.tasks.R
import javax.inject.Inject

class TaskListViewModel @Inject constructor(
    private val getTasksListUseCase: GetTasksListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskListUiState())
    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching {
                getTasksListUseCase()
            }.onSuccess { tasks ->
                _uiState.update { it.copy(isLoading = false, tasks = tasks) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false, errorMessage = R.string.test) }
            }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            runCatching {
                deleteTaskUseCase(taskId)
            }.onSuccess {
                _uiState.update { state ->
                    state.copy(tasks = state.tasks.filter { it.id != taskId })
                }
            }.onFailure {
                _uiState.update { it.copy(errorMessage = R.string.test) }
            }
        }
    }
}