package com.example.bticapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object PreferenceUtil {
    const val PROFILE = "profile"

    fun put(context: Context, key: String, value: String, isSecure: Boolean) {
        val sharedPreferences = getSharePreference(context, isSecure)
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun get(context: Context, key: String, isSecure: Boolean): String? {
        val sharedPreferences = getSharePreference(context, isSecure)
        return sharedPreferences.getString(key, null)
    }

    fun clear(context: Context, key: String, isSecure: Boolean) {
        val sharedPreferences = getSharePreference(context, isSecure)
        sharedPreferences.edit().remove(key)
    }

    private fun getSharePreference(context: Context, isSecure: Boolean): SharedPreferences {
        return if (isSecure) {
            getEncryptedSharedPreferences(context)
        } else {
            context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        }
    }

    private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}