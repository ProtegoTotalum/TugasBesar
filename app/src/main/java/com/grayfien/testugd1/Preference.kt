package com.grayfien.testugd1

import android.content.Context
import android.content.SharedPreferences
import com.grayfien.testugd1.package_room.Pasien
import com.google.gson.Gson

class Preference (var context: Context?) {

    val PRIVATE_MODE = 0

    private val PREF_NAME = "SharedPreferences"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setUser(pasien : Pasien){
        var json = Gson().toJson(pasien)
        editor?.putString("pasien", json)
        editor?.commit()
    }

    fun getUser(): Pasien? {
        var json = Gson().fromJson(pref?.getString("pasien",""), Pasien::class.java)
        return json
    }


}