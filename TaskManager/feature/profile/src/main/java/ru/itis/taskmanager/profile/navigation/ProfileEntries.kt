package ru.itis.taskmanager.profile.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ru.itis.taskmanager.profile.presentation.ProfileRouteScreen

fun EntryProviderScope<NavKey>.profileEntries(
    factory: ViewModelProvider.Factory,
    onLogout: () -> Unit
) {
    entry<ProfileRoute> {
        ProfileRouteScreen(
            factory = factory,
            onLogoutClick = onLogout
        )
    }
}