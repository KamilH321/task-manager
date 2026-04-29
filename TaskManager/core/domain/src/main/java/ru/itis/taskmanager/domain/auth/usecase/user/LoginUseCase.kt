package ru.itis.taskmanager.domain.auth.usecase.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.taskmanager.domain.auth.model.auth.AuthToken
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String): AuthToken {
        return withContext(Dispatchers.IO) {
            repository.login(username, password)
        }
    }
}