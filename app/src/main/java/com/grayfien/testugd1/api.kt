package com.grayfien.testugd1


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
}