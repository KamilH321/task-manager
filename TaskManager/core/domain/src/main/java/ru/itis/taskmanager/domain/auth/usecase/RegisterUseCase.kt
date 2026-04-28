package ru.itis.taskmanager.domain.auth.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.taskmanager.domain.auth.model.User
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String): User {
        return withContext(Dispatchers.IO) {
            repository.register(username, password)
        }
    }
}