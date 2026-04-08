package ru.itis.taskmanager.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.itis.taskmanager.buildconfig.api.BuildConfigProvider
import ru.itis.taskmanager.network.api.AuthApiService
import ru.itis.taskmanager.network.interceptor.ApiKeyInterceptor
import ru.itis.taskmanager.network.interceptor.AuthInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        buildConfigProvider: BuildConfigProvider,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(buildConfigProvider.getApiBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }
}