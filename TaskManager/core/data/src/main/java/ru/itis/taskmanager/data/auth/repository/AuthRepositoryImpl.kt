package ru.itis.taskmanager.data.auth.repository

import ru.itis.taskmanager.data.auth.mapper.toDomain
import ru.itis.taskmanager.domain.auth.model.AuthToken
import ru.itis.taskmanager.domain.auth.model.User
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
        return api.register(
            AuthRequestDto(
                username = username,
                password = password
            )
        ).toDomain()
    }

    override suspend fun login(
        username: String,
        password: String
    ): AuthToken {

        val tokenDto = api.login(
            AuthRequestDto(
                username = username,
                password = password
            )
        )

        sessionRepository.saveAccessToken(tokenDto.accessToken)

        return tokenDto.toDomain()
    }

    override suspend fun getUser(): User {
        return api.getUser().toDomain()
    }
}