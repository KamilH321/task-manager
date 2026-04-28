package ru.itis.taskmanager.domain.auth.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.taskmanager.domain.auth.model.User
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): User {
        return withContext(Dispatchers.IO){
            repository.getUser()
        }
    }
}