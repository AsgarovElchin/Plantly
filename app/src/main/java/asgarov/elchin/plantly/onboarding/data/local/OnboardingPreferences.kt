package asgarov.elchin.plantly.onboarding.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("onboarding_prefs")

class OnboardingPreferences(private val context: Context) {
    private val FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch")

    fun isFirstLaunch(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[FIRST_LAUNCH_KEY] ?: true
        }
    }

    suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_LAUNCH_KEY] = isFirstLaunch
        }
    }
}