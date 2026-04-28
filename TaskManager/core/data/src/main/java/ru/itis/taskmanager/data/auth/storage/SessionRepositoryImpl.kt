package ru.itis.taskmanager.data.auth.storage

import android.content.SharedPreferences
import ru.itis.taskmanager.domain.auth.repository.SessionRepository
import javax.inject.Inject
import androidx.core.content.edit

class SessionRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
): SessionRepository {

    override fun getAccessToken(): String? {
        return preferences.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun saveAccessToken(token: String) {
        preferences.edit {
            putString(KEY_ACCESS_TOKEN, token)
        }
    }

    override fun clearAccessToken() {
        preferences.edit {
            remove(KEY_ACCESS_TOKEN)
        }
    }

    private companion object {
        const val KEY_ACCESS_TOKEN = "key_access_token"
    }
}