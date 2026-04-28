package ru.itis.taskmanager.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ru.itis.taskmanager.profile.navigation.ProfileRoute
import ru.itis.taskmanager.profile.navigation.profileEntries

@Composable
fun MainShellScreen(
    factory: ViewModelProvider.Factory,
    onLogout: () -> Unit
) {
    val navigator: Navigator<NavKey> = remember {
        NavigatorImpl(ProfileRoute)
    }

    val current = navigator.backstack.lastOrNull()

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = current == ProfileRoute,
                    onClick = {
                        navigator.clearAndNavigate(ProfileRoute)
                    },
                    icon = { Text("P") },
                    label = { Text("Профиль") }
                )
            }
        }
    ) { padding ->

        NavDisplay(
            backStack = navigator.backstack,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            onBack = { navigator.pop() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {

                profileEntries(
                    factory = factory,
                    onLogout = onLogout
                )
            }
        )
    }
}