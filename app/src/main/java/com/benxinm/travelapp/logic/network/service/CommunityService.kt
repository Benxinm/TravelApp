package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.logic.network.CommonResultPython
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CommunityService {
    @GET("/community/all_post")
    fun getAllPost(): Call<CommonResultPython<List<List<String>>>>
    @GET("/post_detail/{uid}")
    fun getPostDetail(@Path("uid")id:String): Call<CommonResultPython<List<List<String>>>>
    @GET("/post_picture/{uid}")
    fun getUrls(@Path("uid") id:String): Call<CommonResultPython<List<List<String>>>>
    @GET("/{email}/community/follow_post")
    fun getSubPost(@Path("email")email:String): Call<CommonResultPython<List<List<String>>>>
}