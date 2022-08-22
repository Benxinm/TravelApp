package com.benxinm.travelapp.logic.dao

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first

class SearchHistoryDao(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("history")
        val SEARCH_HISTORY_KEY = stringPreferencesKey("search_history")
    }

    suspend fun getHistory(): HashSet<String>? {
        val preferences = context.dataStore.data.first()
        val gson = Gson()
        val set= if (preferences[SEARCH_HISTORY_KEY]=="{}"||preferences[SEARCH_HISTORY_KEY] ==null){
            HashSet<String>()
        }else{
            gson.fromJson(
                preferences[SEARCH_HISTORY_KEY],
                object : TypeToken<HashSet<String>>() {}.type
            )
        }
        return set
    }
    suspend fun saveHistory(historyJson: String) {
        context.dataStore.edit { preferences ->
            preferences[SEARCH_HISTORY_KEY] = historyJson
        }
    }
}