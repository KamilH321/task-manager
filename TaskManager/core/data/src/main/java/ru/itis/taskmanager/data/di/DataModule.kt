package ru.itis.taskmanager.data.di

import dagger.Binds
import dagger.Module
import ru.itis.taskmanager.data.repository.AuthRepositoryImpl
import ru.itis.taskmanager.data.repository.TaskRepositoryImpl
import ru.itis.taskmanager.data.storage.SessionRepositoryImpl
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import ru.itis.taskmanager.domain.auth.repository.SessionRepository
import ru.itis.taskmanager.domain.auth.repository.TaskRepository
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    fun bindSessionRepository(
        impl: SessionRepositoryImpl
    ): SessionRepository

    @Binds
    @Singleton
    fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository
}