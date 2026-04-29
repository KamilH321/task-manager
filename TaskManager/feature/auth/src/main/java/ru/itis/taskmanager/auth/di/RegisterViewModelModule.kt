package ru.itis.taskmanager.auth.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.itis.taskmanager.auth.presentation.RegisterViewModel
import ru.itis.taskmanager.di.ViewModelKey

@Module
interface RegisterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel
}