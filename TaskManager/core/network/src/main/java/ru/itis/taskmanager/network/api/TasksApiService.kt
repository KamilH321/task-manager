package ru.itis.taskmanager.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.itis.taskmanager.network.pojo.CreateTaskRequestDto
import ru.itis.taskmanager.network.pojo.TaskResponseDto
import ru.itis.taskmanager.network.pojo.UpdateTaskRequestDto

interface TasksApiService {

    @POST("/tasks")
    suspend fun create(
        @Body body: CreateTaskRequestDto
    ): TaskResponseDto

    @GET("/tasks/{taskId}")
    suspend fun get(
        @Path("taskId") taskId: Int
    ): TaskResponseDto

    @GET("/tasks")
    suspend fun getTasksList(): List<TaskResponseDto>

    @PUT("/tasks/{taskId}")
    suspend fun update(
        @Path("taskId") taskId: Int,
        @Body body: UpdateTaskRequestDto
    ): TaskResponseDto

    @DELETE("/tasks/{taskId}")
    suspend fun delete(
        @Path("taskId") taskId: Int
    ): Response<Unit>
}