package ru.itis.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ru.itis.taskmanager.auth.navigation.AuthRoute
import ru.itis.taskmanager.auth.navigation.RegisterRoute
import ru.itis.taskmanager.auth.navigation.authEntries

@Composable
fun AuthNavHost(
    factory: ViewModelProvider.Factory,
    onAuthSuccess: () -> Unit
) {
    val backStack = rememberNavBackStack(AuthRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            authEntries(
                factory = factory,
                onNavigateToRegister = {
                    backStack.add(RegisterRoute)
                },
                onAuthenticated = {
                    onAuthSuccess()
                },
                onBackToAuth = {
                    backStack.removeLastOrNull()
                }
            )
        }
    )
}