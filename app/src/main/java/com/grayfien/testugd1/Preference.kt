package com.grayfien.testugd1

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.grayfien.testugd1.package_room.User

class Preference(var context: Context?) {

    val PRIVATE_MODE = 0

    private val PREF_NAME = "SharedPreferences"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setUser(user: User) {
        var json = Gson().toJson(user)
        editor?.putString("user", json)
        editor?.commit()
    }

    fun getUser(): User? {
        var json = Gson().fromJson(pref?.getString("user", ""), User::class.java)
        return json
    }


}