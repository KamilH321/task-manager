package ru.itis.taskmanager.data.auth.repository

import ru.itis.taskmanager.data.auth.mapper.toDomain
import ru.itis.taskmanager.data.auth.mapper.toRequest
import ru.itis.taskmanager.domain.auth.model.task.CreateTaskParams
import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.model.task.UpdateTaskParams
import ru.itis.taskmanager.domain.auth.repository.TaskRepository
import ru.itis.taskmanager.network.api.TasksApiService
import ru.itis.taskmanager.network.pojo.CreateTaskRequestDto
import javax.inject.Inject
import kotlin.text.get

class TaskRepositoryImpl @Inject constructor(
    private val api: TasksApiService
): TaskRepository {

    override suspend fun createTask(params: CreateTaskParams): Result<Task> = runCatching {
        api.create(params.toRequest()).toDomain()
    }


    override suspend fun getTask(taskId: Int): Result<Task> = runCatching {
        api.get(taskId).toDomain()
    }

    override suspend fun getTaskList(): Result<List<Task>> = runCatching {
        api.getTasksList().map { it.toDomain() }
    }

    override suspend fun updateTask(taskId: Int, params: UpdateTaskParams): Result<Task> = runCatching {
        api.update(taskId, params.toRequest()).toDomain()
    }

    override suspend fun deleteTask(taskId: Int): Result<Unit> = runCatching {
        api.delete(taskId)
    }
}