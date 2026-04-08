package ru.itis.taskmanager.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.taskmanager.buildconfig.api.BuildConfigProvider
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader("X-API-Key", buildConfigProvider.getApiApiKey())
            .build()

        return chain.proceed(request)
    }


}