package ru.itis.taskmanager.auth.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
sealed interface AuthNavKey: NavKey
@Serializable
data object AuthRoute: AuthNavKey

@Serializable
data object RegisterRoute: AuthNavKey