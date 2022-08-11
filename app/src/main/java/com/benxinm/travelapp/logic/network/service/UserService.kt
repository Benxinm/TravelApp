package com.benxinm.travelapp.logic.network.service

import com.benxinm.travelapp.logic.network.CommonResultPython
import com.benxinm.travelapp.logic.network.NoDataResultPython
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("/{email}/fans_count")
    fun getFanSubNum(@Path("email") email:String): Call<CommonResultPython<HashMap<String,Int>>>
    @FormUrlEncoded
    @POST("/{email}/change_nick")
    fun changeNickname(@Path("email") email: String,@Field("new_nick")newNickname:String):Call<NoDataResultPython>
}