package com.grayfien.testugd1

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.grayfien.testugd1.dataClass.UserResponse
import com.grayfien.testugd1.package_room.User

class Preference(var context: Context) {

    val PRIVATE_MODE = 0

    private val PREF_NAME = "SharedPreferences"
    private val IS_LOGIN = "is_login"

    var pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor = pref.edit()

    fun setLogin(isLogin: Boolean) {
        editor.putBoolean(IS_LOGIN, isLogin)
    }

    fun setUser(user: UserResponse.User?) {
        var json = Gson().toJson(user)
        editor.putString("akun", json)
        editor.commit()
    }

    fun getUser(): User? {
        var json = Gson().fromJson(pref.getString("user", ""), User::class.java)
        return json
    }

    fun getUsername(): String? {
        return pref.getString("username", " ")
    }

    fun getEmail(): String? {
        return pref.getString("email", " ")
    }

    fun getTglLahir(): String? {
        return pref.getString("tglLahir", " ")
    }

    fun getNoTelp(): String? {
        return pref.getString("noTelp", " ")
    }

    fun setToken(token: String) {
        editor.putString("token", token)
        editor.commit()
    }

    fun setUsername(username: String) {
        editor.putString("username", username)
        editor.commit()
    }

    fun islogin(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun getToken():String? {
        return pref.getString("token", " ")
    }
}