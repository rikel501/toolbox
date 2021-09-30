package com.toolbox.app.managers.preference

import android.content.SharedPreferences
import com.toolbox.app.App
import com.toolbox.BuildConfig

object PreferencesManager {

    private var mInstance: SharedPreferences = App.getAppContext()
        .getSharedPreferences(BuildConfig.APPLICATION_ID + ".prefs", 0)

    fun setString(key: String, value: String) =
        with(mInstance.edit()) {
            putString(key, value)
            commit()
        }

    fun getString(key: String, defValue: String): String? =
        mInstance.getString(key, defValue)

    fun setInt(key: String, value: Int) =
        with(mInstance.edit()) {
            putInt(key, value)
            commit()
        }

    fun getInt(key: String, defValue: Int): Int =
        mInstance.getInt(key, defValue)

    fun setBoolean(key: String, value: Boolean) =
        with(mInstance.edit()) {
            putBoolean(key, value)
            commit()
        }

    fun getBoolean(key: String, defValue: Boolean): Boolean =
        mInstance.getBoolean(key, defValue)

}