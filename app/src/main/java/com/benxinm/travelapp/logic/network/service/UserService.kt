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
    @POST("/{email}/change_nick")
    fun changeNickname(@Path("email") email: String,@Field("new_nick")newNickname:String):Call<NoDataResultPython>
    @Multipart
    @POST("/{email}/change_head")
    fun uploadImage(@Path("email") email: String,@Part file:MultipartBody.Part):Call<CommonResultPython<HashMap<String,String>>>
    @GET("/{email}/food_collect")
    fun getMyFoodCollect(@Path("email") email: String):Call<CommonResultPython<List<List<String>>>>
    @GET("/{email}/store_collect")
    fun getMyStoreCollect(@Path("email") email: String):Call<CommonResultPython<List<List<String>>>>
}