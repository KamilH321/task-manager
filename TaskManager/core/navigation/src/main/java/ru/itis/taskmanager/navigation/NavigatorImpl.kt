package ru.itis.taskmanager.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

class NavigatorImpl<T: NavKey>(
    start: T
): Navigator<T> {

    override val backstack: SnapshotStateList<T> = mutableStateListOf(start)

    override fun navigate(route: T) {
        backstack.add(route)
    }


    override fun replace(route: T) {
        if (backstack.isNotEmpty()) {
            backstack.removeLastOrNull()
        }
        backstack.add(route)
    }

    override fun pop() {
        if (backstack.size > 1) {
            backstack.removeLastOrNull()
        }
    }

    override fun clearAndNavigate(route: T) {
        backstack.clear()
        backstack.add(route)
    }
}