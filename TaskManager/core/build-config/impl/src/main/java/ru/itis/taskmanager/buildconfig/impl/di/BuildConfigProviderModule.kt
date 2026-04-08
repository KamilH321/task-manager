package ru.itis.taskmanager.buildconfig.impl.di

import dagger.Binds
import dagger.Module
import ru.itis.taskmanager.buildconfig.api.BuildConfigProvider
import ru.itis.taskmanager.buildconfig.impl.BuildConfigProviderImpl

@Module
interface BuildConfigProviderModule {

    @Binds
    fun bindBuildConfigProviderToImpl(impl: BuildConfigProviderImpl): BuildConfigProvider
}