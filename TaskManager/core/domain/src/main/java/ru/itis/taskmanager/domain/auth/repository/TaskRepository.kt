package ru.itis.taskmanager.domain.auth.repository

import ru.itis.taskmanager.domain.auth.model.task.CreateTaskParams
import ru.itis.taskmanager.domain.auth.model.task.Task
import ru.itis.taskmanager.domain.auth.model.task.UpdateTaskParams

interface TaskRepository {

    suspend fun createTask(params: CreateTaskParams): Task

    suspend fun getTask(taskId: Int): Task

    suspend fun getTaskList(): List<Task>

    suspend fun updateTask(params: UpdateTaskParams): Task

    suspend fun deleteTask(taskId: Int): Result<Unit>
}