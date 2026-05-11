package ru.itis.taskmanager.domain.auth.usecase.user

import ru.itis.taskmanager.domain.auth.model.auth.User
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): User {
        return repository.getUser()
    }
}