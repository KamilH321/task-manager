package ru.itis.taskmanager.domain.auth.usecase.task

import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.repository.TaskRepository
import javax.inject.Inject

class GetTasksListUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(): List<Task> {
        return repository.getTaskList()
    }
}