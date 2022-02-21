package com.dimenuto.aboa.data.cache

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.google.gson.Gson

class AppPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private val gson = Gson()

    fun getPreferences(): SharedPreferences {
        return this.sharedPreferences
    }

    fun <T> getValue(name: String, `class`: Class<T>): Any? {
        val value = this.sharedPreferences.getString(name, null)
        return if (TextUtils.isEmpty(value)) null else this.gson.fromJson(value, `class`)
    }

    fun setValue(name: String, value: Any?) {
        this.sharedPreferences.edit().putString(name, this.gson.toJson(value)).apply()
    }

    fun getString(name: String, default: String? = null): String? {
        return this.sharedPreferences.getString(name, default)
    }

    fun setString(name: String, value: String?): AppPreferences {
        this.sharedPreferences.edit().putString(name, value).apply()
        return this
    }

    fun getBoolean(name: String, default: Boolean = false): Boolean {
        return this.sharedPreferences.getBoolean(name, default)
    }

    fun setBoolean(name: String, value: Boolean): AppPreferences {
        this.sharedPreferences.edit().putBoolean(name, value).apply()
        return this
    }

    fun clear() {
        this.sharedPreferences.edit().clear().apply()
    }
}