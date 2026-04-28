package ru.itis.taskmanager.buildconfig.api

interface BuildConfigProvider {

    fun getApiBaseUrl(): String

    fun getApiApiKey(): String
}