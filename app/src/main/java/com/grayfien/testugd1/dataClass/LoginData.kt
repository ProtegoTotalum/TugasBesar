package com.grayfien.testugd1.dataClass

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
)
