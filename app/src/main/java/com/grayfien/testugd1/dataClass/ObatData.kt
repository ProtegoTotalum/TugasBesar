package com.grayfien.testugd1.dataClass

import com.google.gson.annotations.SerializedName

data class ObatData(
    @SerializedName("id_obat") val id_obat: String,
    @SerializedName("nama_pasien") val nama_obat: String,
    @SerializedName("jenis_obat") val jenis_obat: String,
    @SerializedName("deskripsi_obat") val deskripsi_obat: String,
)
