package ru.itis.taskmanager.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.itis.taskmanager.MainActivity
import ru.itis.taskmanager.auth.di.AuthViewModelModule
import ru.itis.taskmanager.auth.di.RegisterViewModelModule
import ru.itis.taskmanager.auth.di.ViewModelFactoryModule
import ru.itis.taskmanager.buildconfig.api.BuildConfigProvider
import ru.itis.taskmanager.data.di.DataModule
import ru.itis.taskmanager.network.di.NetworkModule
import ru.itis.taskmanager.profile.di.ProfileViewModelModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class,
        AuthViewModelModule::class,
        RegisterViewModelModule::class,
        ProfileViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
            @BindsInstance buildConfigProvider: BuildConfigProvider
        ): AppComponent
    }
}