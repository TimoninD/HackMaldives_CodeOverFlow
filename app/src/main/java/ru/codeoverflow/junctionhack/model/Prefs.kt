package ru.codeoverflow.junctionhack.model

import android.content.Context

class Prefs constructor(
    private val context: Context
) {

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    //region auth
    private val AUTH_DATA = "auth_data"
    private val KEY_ACCESS_TOKEN = "access_token"
    private val KEY_USER_ID = "first_launch"
    private val IS_FIRST_ADD_SHOW = "first add show"


    private val authPrefs by lazy { getSharedPreferences(AUTH_DATA) }

    var token: String?
        get() = authPrefs.getString(KEY_ACCESS_TOKEN, null)
        set(value) {
            authPrefs.edit().putString(KEY_ACCESS_TOKEN, value).apply()
        }

    var userId: String?
        get() = authPrefs.getString(KEY_USER_ID, null)
        set(value) {
            authPrefs.edit().putString(KEY_USER_ID, value).apply()
        }

    var isFirstAddShow: Boolean
        get() = authPrefs.getBoolean(IS_FIRST_ADD_SHOW, false)
        set(value) {
            authPrefs.edit().putBoolean(IS_FIRST_ADD_SHOW, value).apply()
        }

}