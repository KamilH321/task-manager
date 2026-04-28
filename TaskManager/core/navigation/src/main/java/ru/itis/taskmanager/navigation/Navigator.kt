package ru.itis.taskmanager.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.StateFlow

interface Navigator<T: NavKey> {

    val backstack: SnapshotStateList<T>

    fun navigate(route: T)

    fun replace(route: T)

    fun pop()

    fun clearAndNavigate(route: T)
}