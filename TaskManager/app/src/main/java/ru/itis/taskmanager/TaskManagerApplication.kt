package ru.itis.taskmanager

import android.app.Application
import ru.itis.taskmanager.buildconfig.impl.BuildConfigProviderImpl
import ru.itis.taskmanager.di.AppComponent
import ru.itis.taskmanager.di.DaggerAppComponent

class TaskManagerApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            context = this,
            buildConfigProvider = BuildConfigProviderImpl()
        )
    }
}