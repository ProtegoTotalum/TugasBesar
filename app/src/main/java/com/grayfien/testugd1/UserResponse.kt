package com.grayfien.testugd1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("status") val stt:String? = null
    @SerializedName("error") val e:Boolean? = null
    @SerializedName("message") val pesan:String? = null
    @SerializedName("data") val data:User? = null

    class User{
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("nama")
        @Expose
        var nama: String? = null

        @SerializedName("username")
        @Expose
        var username: String? = null

        @SerializedName("password")
        @Expose
        var password: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("tglLahir")
        @Expose
        var tglLahir: String? = null

        @SerializedName("noTelp")
        @Expose
        var noTelp: String? = null
    }
}