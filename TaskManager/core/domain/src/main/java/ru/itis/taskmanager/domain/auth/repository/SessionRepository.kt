package ru.itis.taskmanager.domain.auth.repository

interface SessionRepository {

    fun getAccessToken(): String?

    fun saveAccessToken(token: String)

    fun clearAccessToken()
}