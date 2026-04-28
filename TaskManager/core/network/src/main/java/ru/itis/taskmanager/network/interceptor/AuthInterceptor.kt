package ru.itis.taskmanager.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.taskmanager.domain.auth.repository.SessionRepository
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionRepository: SessionRepository
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = sessionRepository.getAccessToken()

        val request = if (token.isNullOrBlank()) {
            chain.request()
        } else {
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }

        return chain.proceed(request)
    }
}