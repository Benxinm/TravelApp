package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.data.User
import com.benxinm.travelapp.logic.network.CommonResultPython
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("user_name") username: String,
        @Field("password") password: String
    ): Call<CommonResultPython<HashMap<String,String>>>

    @FormUrlEncoded
    @POST("/register")
    fun register(
        @Field("user_name") username: String,
        @Field("user_nick") nickname: String,
        @Field("password1") password: String,
        @Field("password2") rePassword: String
    ): Call<CommonResultPython<User>>

    @FormUrlEncoded
    @POST("/send_email")
    fun sendEmail(@Field("mail_account") mail: String): Call<CommonResultPython<HashMap<String, String>>>

    @FormUrlEncoded
    @POST("/verify_code")
    fun verifyCode(@Field("email_code") code:String):Call<CommonResultPython<HashMap<String, String>>>
}