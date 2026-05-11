package ru.itis.taskmanager.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.taskmanager.data.mapper.toDomain
import ru.itis.taskmanager.data.mapper.toRequest
import ru.itis.taskmanager.domain.auth.model.task.CreateTaskParams
import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.model.task.UpdateTaskParams
import ru.itis.taskmanager.domain.auth.repository.TaskRepository
import ru.itis.taskmanager.network.api.TasksApiService
import javax.inject.Inject
import kotlin.text.get

class TaskRepositoryImpl @Inject constructor(
    private val api: TasksApiService
): TaskRepository {

    override suspend fun createTask(params: CreateTaskParams): Task =
        withContext(Dispatchers.IO) {
            api.create(params.toRequest()).toDomain()
        }

    override suspend fun getTask(taskId: Int): Task =
        withContext(Dispatchers.IO) {
            api.get(taskId).toDomain()
        }

    override suspend fun getTaskList(): List<Task> =
        withContext(Dispatchers.IO) {
            api.getTasksList().map { it.toDomain() }
        }

    override suspend fun updateTask(taskId: Int, params: UpdateTaskParams): Task =
        withContext(Dispatchers.IO) {
            api.update(taskId, params.toRequest()).toDomain()
        }

    override suspend fun deleteTask(taskId: Int) =
        withContext(Dispatchers.IO) {
            api.delete(taskId)
        }
}