package com.benxinm.travelapp.logic.dao

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDao(private val context: Context) {
    companion object{
        private val Context.dataStore:DataStore<Preferences> by preferencesDataStore("user")
        val USER_IS_LOGIN= booleanPreferencesKey("user_is_login")
        val USER_EMAIL_KEY= stringPreferencesKey("user_email")
        val USER_PASSWORD_KEY= stringPreferencesKey("user_password")
        val USER_IMAGE_KEY= stringPreferencesKey("user_image")
    }
    val getUserStatus: Flow<Boolean?> =context.dataStore.data.map { preferences->
        preferences[USER_IS_LOGIN]?:false
    }
    suspend fun changeUserStatus(target:Boolean){
        context.dataStore.edit { preferences->
            preferences[USER_IS_LOGIN]=target
        }
    }
    val getUserEmail: Flow<String?> = context.dataStore.data.map { preferences->
        preferences[USER_EMAIL_KEY]?:""
    }

    suspend fun saveUserEmail(email:String){
        context.dataStore.edit { preferences->
            preferences[USER_EMAIL_KEY]=email
        }
    }
    val getPassword:Flow<String?> = context.dataStore.data.map {preferences->
        preferences[USER_PASSWORD_KEY]?:""
    }
    suspend fun saveUserImage(uri:String){
        context.dataStore.edit { preferences->
            preferences[USER_IMAGE_KEY]=uri
        }
    }
    val getImage:Flow<String> = context.dataStore.data.map {preferences->
        preferences[USER_IMAGE_KEY]?:""
    }
    suspend fun saveUserPassword(password:String){
        context.dataStore.edit { preferences->
            preferences[USER_PASSWORD_KEY]=password
        }
    }
}