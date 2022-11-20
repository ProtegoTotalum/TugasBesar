package com.grayfien.testugd1.retrofit

import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("pasien/{cari}")
    fun getSearch(@Path("cari") cari:String? = null):
            Call<ResponseDataPasien>

    @GET("pasien/{id}")
    fun getData(@Path("id") id:Int? = null):
            Call<ResponseDataPasien>
    @FormUrlEncoded
    @POST("pasien")
    fun createData(
        @Field("id_pasien") id_pasien:Int?,
        @Field("nama_pasien") nama_pasien:String?,
        @Field("email") email:String?,
        @Field("tglLahir") tglLahir:String?,
        @Field("noTelp") noTelp:String?,
    ):Call<ResponseCreate>
    @DELETE("pasien/{id_pasien}")
    fun deleteData(@Path("id_pasien")id_pasien:
                   Int?):Call<ResponseCreate>
    @FormUrlEncoded
    @PUT("pasien/{id_pasien}")
    fun updateData(
        @Field("id_pasien") id_pasien:Int?,
        @Field("mhsnama") mhsnama:String?,
        @Field("mhsalamat") mhsalamat:String?,
        @Field("prodinama") prodinama:String?,
        @Field("mhstgllhr") mhstgllhr:String?,
    ):Call<ResponseCreate>
}