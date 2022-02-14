package com.binar.suitgamechallenge.data.preference;

import android.content.Context
import android.content.SharedPreferences

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class UserPreference(context: Context) {
    private var preference: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    companion object {
        private const val NAME = "SuitGame" //app name or else
        private const val MODE = Context.MODE_PRIVATE
        private val PREF_NAME_PLAYER = Pair("PREF_NAME_PLAYER", null)
        private val PREF_IS_APP_ALREADY_OPEN_FIRST_TIME =
            Pair("PREF_IS_APP_ALREADY_OPEN_FIRST_TIME", null)
    }

    var name: String?
        get() = preference.getString(PREF_NAME_PLAYER.first, PREF_NAME_PLAYER.second)
        set(value) = preference.edit {
            it.putString(PREF_NAME_PLAYER.first, value)
        }

//    var isAppAlreadyOpenFirstTime: Boolean
//    get() = preference.getBoolean(
//        PREF_IS_APP_ALREADY_OPEN_FIRST_TIME.first,
//        PREF_IS_APP_ALREADY_OPEN_FIRST_TIME.second
//    )
//    set(value) = preference.edit()

}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}

private fun SharedPreferences.delete() {
    edit().clear().apply()
}