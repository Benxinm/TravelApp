package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.logic.network.CommonResultPython
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OtherDetailService {
    @GET("/food/{uid}/detail")
    fun getIntroHis(@Path("uid") target:String): Call<CommonResultPython<List<List<String>>>>
    @GET("/food/{uid}/practice")
    fun getRecipe(@Path("uid") target: String):Call<CommonResultPython<List<List<String>>>>
    @GET("/food/{uid}/related_stores")
    fun getRestaurant(@Path("uid") target: String):Call<CommonResultPython<List<List<String>>>>
    @GET("/{address}/food")
    fun getFood(@Path("address") address:String):Call<CommonResultPython<List<List<String>>>>
    @GET("/food/{uid}/related_sites")
    fun getRelatedSight(@Path("uid")target: String):Call<CommonResultPython<List<List<String>>>>
}