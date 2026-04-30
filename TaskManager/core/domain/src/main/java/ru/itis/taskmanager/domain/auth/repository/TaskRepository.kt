package ru.itis.taskmanager.domain.auth.repository

import ru.itis.taskmanager.domain.auth.model.task.CreateTaskParams
import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.model.task.UpdateTaskParams

interface TaskRepository {

    suspend fun createTask(params: CreateTaskParams): Result<Task>

    suspend fun getTask(taskId: Int): Result<Task>

    suspend fun getTaskList(): Result<List<Task>>

    suspend fun updateTask(taskId: Int, params: UpdateTaskParams): Result<Task>

    suspend fun deleteTask(taskId: Int): Result<Unit>
}