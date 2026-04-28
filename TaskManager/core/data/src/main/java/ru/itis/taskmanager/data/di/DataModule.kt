package ru.itis.taskmanager.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.itis.taskmanager.data.auth.repository.AuthRepositoryImpl
import ru.itis.taskmanager.data.auth.storage.SessionRepositoryImpl
import ru.itis.taskmanager.domain.auth.repository.AuthRepository
import ru.itis.taskmanager.domain.auth.repository.SessionRepository
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
}