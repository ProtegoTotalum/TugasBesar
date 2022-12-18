package com.grayfien.testugd1.dataClass

import com.google.gson.annotations.SerializedName

data class SupplierData(
    @SerializedName("id_supplier") val id_supplier: String,
    @SerializedName("nama_supplier") val nama_supplier: String,
    @SerializedName("email_supplier") val email_supplier: String,
    @SerializedName("noTelp_supplier") val noTelp_supplier: String,
)
