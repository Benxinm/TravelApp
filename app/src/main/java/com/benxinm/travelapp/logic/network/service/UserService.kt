package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.logic.network.CommonResultPython
import com.benxinm.travelapp.logic.network.NoDataResultPython
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("/{email}/fans_count")
    fun getFanSubNum(@Path("email") email:String): Call<CommonResultPython<HashMap<String,Int>>>
    @FormUrlEncoded
    @POST("/change_name/{email}")
    fun changeNickname(@Header("Authorization")token:String,@Path("email") email: String,@Field("new_nick")newNickname:String):Call<CommonResultPython<String>>
    @FormUrlEncoded
    @POST("/{email}/change_password")
    fun changePassword(@Header("Authorization")token:String,@Path("email") email: String,@Field("new_password1")password1:String,@Field("new_password2")password2:String):Call<CommonResultPython<String>>
    @Multipart
    @POST("/{email}/change_head")
    fun uploadImage(@Header("Authorization") token:String,@Path("email") email: String,@Part file:MultipartBody.Part):Call<CommonResultPython<HashMap<String,String>>>
    @GET("/{email}/food_collect")
    fun getMyFoodCollect(@Path("email") email: String):Call<CommonResultPython<List<List<String>>>>
    @GET("/{email}/store_collect")
    fun getMyStoreCollect(@Path("email") email: String):Call<CommonResultPython<List<List<String>>>>
    @GET("/{email1}/{email2}")
    fun follow(@Header("Authorization") token:String, @Path("email1") email: String, @Path("email2") target:String):Call<CommonResultPython<String>>
    @FormUrlEncoded
    @POST("/{email}/set_bottle")
    fun setBottle(@Header("Authorization")token: String,@Path("email") email: String,@Field("type")  type:String,@Field("degree") degree:String):Call<NoDataResultPython>
    @GET("/{email}/check_bottle")
    fun checkBottle(@Header("Authorization")token: String,@Path("email") email: String):Call<CommonResultPython<List<List<String>>>>
    @GET("/{email}/my_post")
    fun getMyPost(@Header("Authorization")token: String,@Path("email") email: String):Call<CommonResultPython<List<List<String>>>>
}