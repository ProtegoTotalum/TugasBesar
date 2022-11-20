package com.grayfien.testugd1.retrofit

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PasienData(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id_pasien") val id:Int,

    @SerializedName("nama_pasien") val nama_pasien:String,
    @SerializedName("email") val email:String,
    @SerializedName("tglLahir") val tglLahir:String,
    @SerializedName("noTelp") val noTelp:String,
)