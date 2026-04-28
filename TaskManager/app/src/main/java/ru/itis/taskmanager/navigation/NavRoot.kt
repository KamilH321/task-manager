package ru.itis.taskmanager.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ru.itis.taskmanager.designsystem.theme.TaskManagerTheme

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun TaskManagerNavRoot(
    factory: ViewModelProvider.Factory
) {
    TaskManagerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            val navigator = remember {
                NavigatorImpl<RootRoute>(AuthGraph)
            }

            NavDisplay(
                backStack = navigator.backstack,
                onBack = { navigator.pop() },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {

                    entry<AuthGraph> {
                        AuthNavHost(
                            factory = factory,
                            onAuthSuccess = {
                                navigator.clearAndNavigate(MainGraph)
                            }
                        )
                    }

                    entry<MainGraph> {
                        MainShellScreen(
                            factory = factory,
                            onLogout = {
                                navigator.clearAndNavigate(AuthGraph)
                            }
                        )
                    }
                }
            )
        }
    }
}