package ru.itis.taskmanager.data.auth.mapper

import ru.itis.taskmanager.domain.auth.model.auth.AuthToken
import ru.itis.taskmanager.domain.auth.model.auth.User
import ru.itis.taskmanager.network.pojo.AuthTokenDto
import ru.itis.taskmanager.network.pojo.UserDto

fun UserDto.toDomain(): User = User(
    id = id,
    username = username,
    createdAt = createdAt
)

fun AuthTokenDto.toDomain(): AuthToken = AuthToken(
    accessToken = accessToken,
    tokenType = tokenType
)