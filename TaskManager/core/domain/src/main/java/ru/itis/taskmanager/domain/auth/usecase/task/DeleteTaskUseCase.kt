package ru.itis.taskmanager.domain.auth.usecase.task

import ru.itis.taskmanager.domain.auth.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(taskId: Int): Result<Unit> =
        repository.deleteTask(taskId)
}