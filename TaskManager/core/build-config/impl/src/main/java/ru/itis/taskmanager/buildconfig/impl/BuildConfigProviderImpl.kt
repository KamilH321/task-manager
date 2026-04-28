package ru.itis.taskmanager.buildconfig.impl

import ru.itis.taskmanager.buildconfig.api.BuildConfigProvider

class BuildConfigProviderImpl: BuildConfigProvider {

    override fun getApiBaseUrl(): String = BuildConfig.API_BASE_URL

    override fun getApiApiKey(): String = BuildConfig.API_KEY
}