package com.grayfien.testugd1.retrofit

import com.google.gson.annotations.SerializedName

data class ResponseDataPasien(
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data: List<PasienData>
)
