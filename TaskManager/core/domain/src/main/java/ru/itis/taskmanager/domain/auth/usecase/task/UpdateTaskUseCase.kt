package ru.itis.taskmanager.domain.auth.usecase.task

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.model.task.UpdateTaskParams
import ru.itis.taskmanager.domain.auth.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(taskId: Int, params: UpdateTaskParams): Result<Task> {
        return withContext(Dispatchers.IO) {
            repository.updateTask(taskId, params)
        }
    }
}