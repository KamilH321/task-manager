package ru.itis.taskmanager.profile.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.itis.taskmanager.profile.presentation.ProfileViewModel
import ru.itis.taskmanager.utils.di.ViewModelKey

@Module
interface ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}