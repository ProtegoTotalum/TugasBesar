package com.grayfien.testugd1.dataClass

import com.google.gson.annotations.SerializedName

data class PasienData(
    @SerializedName("id_pasien") val id_pasien: String,
    @SerializedName("nama_pasien") val nama_pasien: String,
    @SerializedName("email_pasien") val email_pasien: String,
    @SerializedName("tglLahir_pasien") val tglLahir_pasien: String,
    @SerializedName("noTelp_pasien") val noTelp_pasien: String,
)
