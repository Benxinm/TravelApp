package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.data.Comment
import com.benxinm.travelapp.data.CommentWithHead
import com.benxinm.travelapp.logic.network.CommonResult
import retrofit2.Call
import retrofit2.http.*

interface DetailService {
    @GET("/grade/add/{type}/{target}")
    fun addLike(
        @Path("type") type: String,
        @Path("target") target: String
    ): Call<CommonResult<String>>

    @GET("/grade/cancel/{type}/{target}")
    fun cancelLike(
        @Path("type") type: String,
        @Path("target") target: String
    ): Call<CommonResult<String>>

    @FormUrlEncoded
    @POST("/comment/add")
    fun addComment(
        @Header("token") token:String,
        @Field("userName") email: String,
        @Field("type") type: Int,
        @Field("word") text: String,
        @Field("target") target: String,
        @Field("level") level: Int
    ):Call<CommonResult<Comment>>
    @DELETE("/comment/delete/{id}")
    fun deleteComment(
        @Path("id") id:String
    ):Call<CommonResult<String>>
    @PUT("/comment/update")
    fun updateComment(

        @Field("id") id:String,
        @Field("userName") email: String,
        @Field("type") type: Int,
        @Field("word")text: String,
        @Field("target") target: String,
        @Field("level") level:Int,
        @Field("grade")likes:Int,
    ):Call<CommonResult<Comment>>
    @GET("/comment/page/{type}/{target}/{level}/{page}/{pageSize}")
    fun getComments(
        @Path("type") type: Int,
        @Path("target") target: String,
        @Path("level") level:Int,
        @Path("page") page:Int,
        @Path("pageSize") pageSize:Int,
    ):Call<CommonResult<List<CommentWithHead>>>

}