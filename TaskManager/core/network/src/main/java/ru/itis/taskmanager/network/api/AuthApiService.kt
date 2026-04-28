package ru.itis.taskmanager.network.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.itis.taskmanager.network.pojo.AuthRequestDto
import ru.itis.taskmanager.network.pojo.AuthTokenDto
import ru.itis.taskmanager.network.pojo.UserDto

interface AuthApiService {

    @POST("auth/register")
    suspend fun register(
        @Body body: AuthRequestDto
    ): UserDto

    @POST("auth/login")
    suspend fun login(
        @Body body: AuthRequestDto
    ): AuthTokenDto

    @GET("auth/me")
    suspend fun getUser(): UserDto
}