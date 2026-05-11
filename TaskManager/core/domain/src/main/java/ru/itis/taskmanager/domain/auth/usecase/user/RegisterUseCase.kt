package ru.itis.taskmanager.domain.auth.usecase.user

import ru.itis.taskmanager.domain.auth.model.auth.User
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String): User {
        return repository.register(username, password)
    }
}