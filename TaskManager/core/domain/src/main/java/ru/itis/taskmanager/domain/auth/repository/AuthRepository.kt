package ru.itis.taskmanager.domain.auth.repository

import ru.itis.taskmanager.domain.auth.model.AuthToken
import ru.itis.taskmanager.domain.auth.model.User

interface AuthRepository {

    suspend fun register(username: String, password: String): User

    suspend fun login(username: String, password: String): AuthToken

    suspend fun getUser(): User
}