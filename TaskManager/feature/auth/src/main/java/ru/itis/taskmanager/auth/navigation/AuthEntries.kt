package ru.itis.taskmanager.auth.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ru.itis.taskmanager.auth.presentation.AuthRouteScreen
import ru.itis.taskmanager.auth.presentation.RegisterRouteScreen

fun EntryProviderScope<NavKey>.authEntries(
    factory: ViewModelProvider.Factory,
    onNavigateToRegister: () -> Unit,
    onAuthenticated: () -> Unit,
    onBackToAuth: () -> Unit
) {
    entry<AuthRoute> {
        AuthRouteScreen(
            factory = factory,
            onNavigateToRegister = onNavigateToRegister,
            onAuthenticated = onAuthenticated
        )
    }

    entry<RegisterRoute> {
        RegisterRouteScreen(
            factory = factory,
            onBackToAuth = onBackToAuth
        )
    }
}