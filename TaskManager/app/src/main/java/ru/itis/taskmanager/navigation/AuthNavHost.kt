package ru.itis.taskmanager.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ru.itis.taskmanager.auth.navigation.AuthNavKey
import ru.itis.taskmanager.auth.navigation.AuthRoute
import ru.itis.taskmanager.auth.navigation.RegisterRoute
import ru.itis.taskmanager.auth.navigation.authEntries


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun AuthNavHost(
    factory: ViewModelProvider.Factory,
    onAuthSuccess: () -> Unit
) {
    val navigator = remember {
        NavigatorImpl<AuthNavKey>(AuthRoute)
    }

    NavDisplay(
        backStack = navigator.backstack,
        onBack = { navigator.pop() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            authEntries(
                factory = factory,
                onNavigateToRegister = {
                    navigator.navigate(RegisterRoute)
                },
                onAuthenticated = {
                    onAuthSuccess()
                },
                onBackToAuth = {
                   navigator.pop()
                }
            )
        }
    )
}