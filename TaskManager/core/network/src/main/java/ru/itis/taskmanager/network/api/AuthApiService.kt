package ru.itis.taskmanager.network.api

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST
    suspend fun register(
    )
}