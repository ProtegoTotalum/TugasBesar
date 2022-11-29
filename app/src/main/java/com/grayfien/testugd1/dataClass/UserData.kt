package com.grayfien.testugd1.dataClass

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id") val id: String,
    @SerializedName("nama") val nama: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email: String,
    @SerializedName("tglLahir") val tgLahir: String,
    @SerializedName("noTelp") val noTelp: String,
)
