package com.loanwalle.loanwallecollection.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManegar {

    var LOGIN_STATE = "login_state"
    var IS_LOGIN = "is_login"
    var DATA_PROFILE = "data_profile"

    private fun getPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
    }

    fun saveString(context: Context, key: String?, value: String?) {
        getPreference(context)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun saveBoolean(context: Context, key: String?, value: Boolean) {
        getPreference(context)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun saveInt(context: Context, key: String?, value: Int) {
        getPreference(context)
            .edit()
            .putInt(key, value)
            .apply()
    }

    fun getString(context: Context, key: String?): String? {
        return getPreference(context)
            .getString(key, "")
    }

    fun getInt(context: Context, key: String?): Int {
        return getPreference(context)
            .getInt(key, 0)
    }

    fun getBoolean(context: Context, key: String?): Boolean {
        return getPreference(context).getBoolean(key, false)
    }

    fun clearAll(context: Context) {
        getPreference(context)
            .edit()
            .clear()
            .apply()
    }

    fun remove(context: Context, key: String?) {
        getPreference(context)
            .edit()
            .remove(key)
            .apply()
    }
}