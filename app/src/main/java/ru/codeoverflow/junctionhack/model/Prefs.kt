package ru.codeoverflow.junctionhack.model

import android.content.Context

class Prefs constructor(
    private val context: Context
) {

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    companion object {
        private const val AUTH_DATA = "auth_data"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val IS_TEST_SHOW = "test show"
        private const val IS_BUDGET_SHOW = "budget show"
        private const val BUDGET = "budget"
    }


    private val authPrefs by lazy { getSharedPreferences(AUTH_DATA) }

    var token: String?
        get() = authPrefs.getString(KEY_ACCESS_TOKEN, "")
        set(value) {
            authPrefs.edit().putString(KEY_ACCESS_TOKEN, value).apply()
        }

    var isTestShow: Boolean
        get() = authPrefs.getBoolean(IS_TEST_SHOW, false)
        set(value) {
            authPrefs.edit().putBoolean(IS_TEST_SHOW, value).apply()
        }

    var isBudgetShow: Boolean
        get() = authPrefs.getBoolean(IS_BUDGET_SHOW, false)
        set(value) {
            authPrefs.edit().putBoolean(IS_BUDGET_SHOW, value).apply()
        }

    var budget: Int
        get() = authPrefs.getInt(BUDGET, 0)
        set(value) {
            authPrefs.edit().putInt(BUDGET, value).apply()
        }

}