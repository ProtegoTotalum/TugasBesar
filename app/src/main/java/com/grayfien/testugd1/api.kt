package com.grayfien.testugd1


import com.grayfien.testugd1.dataClass.*
import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("akun/{cari}")
    fun getData(@Path("cari") cari:String? = null):
            Call<ResponseDataUser>

    @FormUrlEncoded
    @POST("akun")
    fun createData(
        @Field("id") id: String?,
        @Field("nama") nama: String?,
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("email") email: String?,
        @Field("tglLahir") tglLahir: String?,
        @Field("noTelp") noTelp: String?,
    ): Call<ResponseCreate>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username:String?,
        @Field("password") password:String?,
    ):Call<UserResponse>

    @FormUrlEncoded
    @PUT("akun/{id}")
    fun updateData(
        @Path("id") id: String?,
        @Field("nama") nama: String?,
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("email") email: String?,
        @Field("tglLahir") tglLahir: String?,
        @Field("noTelp") noTelp: String?,
    ): Call<ResponseCreate>



    @GET("pasien/{cari}")
    fun getDataPasien(@Path("cari") cari: String? = null):
            Call<ResponseDataPasien>

    @GET("pasien/{id}")
    fun getSearch(@Path("id_pasien") id: String? = null):
            Call<ResponseDataPasien>

    @FormUrlEncoded
    @POST("pasien")
    fun createDataPasien(
        @Field("id_pasien") id_pasien: String?,
        @Field("nama_pasien") nama_pasien: String?,
        @Field("email_pasien") email: String?,
        @Field("tglLahir_pasien") tglLahir: String?,
        @Field("noTelp_pasien") noTelp: String?,
    ): Call<ResponseCreate>

    @DELETE("pasien/{id_pasien}")
    fun deleteDataPasien(
        @Path("id_pasien") id_pasien:
        String?
    ): Call<ResponseCreate>

    @FormUrlEncoded
    @PUT("pasien/{id_pasien}")
    fun updateDataPasien(
        @Path("id_pasien") id_pasien: String?,
        @Field("nama_pasien") nama_pasien: String?,
        @Field("email_pasien") email_pasien: String?,
        @Field("tglLahir_pasien") tglLahir_pasien: String?,
        @Field("noTelp_pasien") noTelpPasien: String?,
    ): Call<ResponseCreate>




    @GET("obat/{cari}")
    fun getDataObat(@Path("cari") cari: String? = null):
            Call<ResponseDataObat>

    @FormUrlEncoded
    @POST("obat")
    fun createDataObat(
        @Field("id_obat") id_obat: String?,
        @Field("nama_obat") nama_obat: String?,
        @Field("jenis_obat") jenis_obat: String?,
        @Field("deskripsi_obat") deskripsi_obat: String?,
    ): Call<ResponseCreate>

    @DELETE("obat/{id_obat}")
    fun deleteDataObat(
        @Path("id_obat") id_obat:
        String?
    ): Call<ResponseCreate>

    @FormUrlEncoded
    @PUT("obat/{id_obat}")
    fun updateDataObat(
        @Path("id_obat") id_obat: String?,
        @Field("nama_obat") nama_obat: String?,
        @Field("jenis_obat") jenis_obat: String?,
        @Field("deskripsi_obat") deskripsi_obat: String?,
    ): Call<ResponseCreate>



    @GET("supplier/{cari}")
    fun getDataSupplier(@Path("cari") cari: String? = null):
            Call<ResponseDataSupplier>

    @FormUrlEncoded
    @POST("supplier")
    fun createDataSupplier(
        @Field("id_supplier") id_supplier: String?,
        @Field("nama_supplier") nama_supplier: String?,
        @Field("email_supplier") email_supplier: String?,
        @Field("noTelp_supplier") noTelp_supplier: String?,
    ): Call<ResponseCreate>

    @DELETE("supplier/{id_supplier}")
    fun deleteDataSupplier(
        @Path("id_supplier") id_supplier:
        String?
    ): Call<ResponseCreate>

    @FormUrlEncoded
    @PUT("supplier/{id_supplier}")
    fun updateDataSupplier(
        @Path("id_supplier") id_supplier: String?,
        @Field("nama_supplier") nama_supplier: String?,
        @Field("email_supplier") email_supplier: String?,
        @Field("noTelp_supplier") noTelp_supplier: String?,
    ): Call<ResponseCreate>
}