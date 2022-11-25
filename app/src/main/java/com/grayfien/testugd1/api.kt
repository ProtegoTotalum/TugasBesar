package com.grayfien.testugd1


import com.grayfien.testugd1.dataClass.ResponseDataPasien
import com.grayfien.testugd1.dataClass.ResponseDataUser
import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("akun/{cari}")
    fun getData(@Path("cari") cari:String? = null):
            Call<ResponseDataUser>


    @FormUrlEncoded
    @POST("akun")
    fun createData(
        @Field("id") id:String?,
        @Field("nama") nama:String?,
        @Field("username") username:String?,
        @Field("password") password:String?,
        @Field("email") email:String?,
        @Field("tglLahir") tglLahir:String?,
        @Field("noTelp") noTelp:String?,
    ):Call<ResponseCreate>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username:String?,
        @Field("password") password:String?,
    ):Call<UserResponse>

    @FormUrlEncoded
    @PUT("akun/{id}")
    fun updateData(
        @Field("id") id:String?,
        @Field("nama") nama:String?,
        @Field("username") username:String?,
        @Field("password") password:String?,
        @Field("email") email:String?,
        @Field("tglLahir") tglLahir:String?,
        @Field("noTelp") noTelp:String?,
    ):Call<ResponseCreate>



    @GET("pasien/{cari}")
    fun getDataPasien(@Path("cari") cari:String? = null):
            Call<ResponseDataPasien>

    @GET("pasien/{id}")
    fun getSearch(@Path("id_pasien") id:String? = null):
            Call<ResponseDataPasien>

    @FormUrlEncoded
    @POST("pasien")
    fun createDataPasien(
        @Field("id_pasien") id_pasien:String?,
        @Field("nama_pasien") nama_pasien:String?,
        @Field("email_pasien") email:String?,
        @Field("tglLahir_pasien") tglLahir:String?,
        @Field("noTelp_pasien") noTelp:String?,
    ):Call<ResponseCreate>

    @DELETE("pasien/{id_pasien}")
    fun deleteDataPasien(@Path("id_pasien")id_pasien:
                   String?):Call<ResponseCreate>

    @FormUrlEncoded
    @PUT("pasien/{id_pasien}")
    fun updateDataPasien(
        @Field("id_pasien") id_pasien:String?,
        @Field("nama_pasien") nama_pasien: String?,
        @Field("email_pasien") email_pasien:String?,
        @Field("tglLahir_pasien") tglLahir_pasien:String?,
        @Field("noTelp_pasien") noTelpPasien:String?,
    ):Call<ResponseCreate>
}