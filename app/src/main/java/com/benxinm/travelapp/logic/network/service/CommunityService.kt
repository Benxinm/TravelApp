package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.logic.network.CommonResultPython
import com.benxinm.travelapp.logic.network.NoDataResultPython
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface CommunityService {
    @GET("/community/all_post")
    fun getAllPost(): Call<CommonResultPython<List<List<String>>>>
    @GET("/post_detail/{uid}")
    fun getPostDetail(@Header("Authorization") token:String, @Path("uid")id:String): Call<CommonResultPython<List<List<String>>>>
    @GET("/post_picture/{uid}")
    fun getUrls(@Header("Authorization") token:String,@Path("uid") id:String): Call<CommonResultPython<List<List<String>>>>
    @GET("/{email}/community/follow_post")
    fun getSubPost(@Header("Authorization") token:String,@Path("email")email:String): Call<CommonResultPython<List<List<String>>>>
    @Multipart
    @POST("/{email}/up_post")
    fun addPost(@Header("Authorization") token:String, @Path("email") email: String, @Part title: MultipartBody.Part, @Part text: MultipartBody.Part, @Part fileList:List<MultipartBody.Part>):Call<NoDataResultPython>
}