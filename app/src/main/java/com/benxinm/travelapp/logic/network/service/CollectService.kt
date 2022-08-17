package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.data.responseModel.CollectModel
import com.benxinm.travelapp.logic.network.CommonResult
import retrofit2.Call
import retrofit2.http.*

interface CollectService {
    @FormUrlEncoded
    @POST("/add")
    fun addCollect(
        @Header("token") token: String,
        @Field("userName") email: String,
        @Field("type") type: Int,
        @Field("target") target: String,
        @Field("picture") firstUrl: String
    ): Call<CommonResult<String>>

    @DELETE("/collect/delete/{id}")
    fun deleteCollect(
        @Header("token") token: String,
        @Path("id") id: String
    ): Call<CommonResult<String>>

    @FormUrlEncoded
    @PUT("/collect/update")
    fun updateCollect(
        @Header("token") token: String,
        @Field("userName") email: String,
        @Field("type") type: Int,
        @Field("target") target: String,
        @Field("picture") firstUrl: String
    ): Call<CommonResult<CollectModel>>

    @GET("/collect/page/{userName}/{type}/{page}/{pageSize}")
    fun getCollects(
        @Header("token") token: String,
        @Path("userName") email: String,
        @Path("type") type: Int,
        @Path("page") page: Int,
        @Path("pageSize") pageSize: Int
    ): Call<CommonResult<List<CollectModel>>>

    @GET("/collect/count/{target}")
    fun getCollectCount(
        @Header("token") token: String,
        @Path("target") target: String
    ):Call<CommonResult<String>>
}