package ru.itis.taskmanager.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.taskmanager.data.mapper.toDomain
import ru.itis.taskmanager.domain.auth.model.auth.AuthToken
import ru.itis.taskmanager.domain.auth.model.auth.User
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import ru.itis.taskmanager.domain.auth.repository.SessionRepository
import ru.itis.taskmanager.network.api.AuthApiService
import ru.itis.taskmanager.network.pojo.AuthRequestDto
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApiService,
    private val sessionRepository: SessionRepository
): AuthRepository {

    override suspend fun register(
        username: String,
        password: String
    ): User {
        return withContext(Dispatchers.IO) {
            api.register(
                AuthRequestDto(
                    username = username,
                    password = password
                )
            ).toDomain()
        }
    }

    override suspend fun login(
        username: String,
        password: String
    ): AuthToken {

        val tokenDto =  api.login(
            AuthRequestDto(
                username = username,
                password = password
            )
        )

        sessionRepository.saveAccessToken(tokenDto.accessToken)

        return withContext(Dispatchers.IO) {
            tokenDto.toDomain()
        }
    }

    override suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            api.getUser().toDomain()
        }
    }
}