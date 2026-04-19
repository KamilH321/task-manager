package ru.itis.taskmanager.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ru.itis.taskmanager.auth.navigation.AuthRoute
import ru.itis.taskmanager.auth.navigation.RegisterRoute
import ru.itis.taskmanager.auth.navigation.authEntries
import ru.itis.taskmanager.designsystem.theme.TaskManagerTheme
import ru.itis.taskmanager.profile.navigation.ProfileRoute
import ru.itis.taskmanager.profile.navigation.profileEntries


@Composable
fun TaskManagerNavRoot(
    factory: ViewModelProvider.Factory
) {
    TaskManagerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            val backStack = rememberNavBackStack(AuthGraph)

            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {

                    entry<AuthGraph> {
                        AuthNavHost(
                            factory = factory,
                            onAuthSuccess = {
                                backStack.clear()
                                backStack.add(MainGraph)
                            }
                        )
                    }

                    entry<MainGraph> {
                        MainShellScreen(
                            factory = factory,
                            onLogout = {
                                backStack.clear()
                                backStack.add(AuthGraph)
                            }
                        )
                    }
                }
            )
        }
    }
}