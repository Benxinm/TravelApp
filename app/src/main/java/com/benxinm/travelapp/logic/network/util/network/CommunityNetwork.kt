package com.benxinm.travelapp.logic.network.util.network

import com.benxinm.travelapp.logic.network.service.CommunityService
import com.benxinm.travelapp.logic.network.util.PythonServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CommunityNetwork {
    private val communityService=PythonServiceCreator.create(CommunityService::class.java)
    suspend fun getUrl(token:String,id:String)= communityService.getUrls(token,id).await()
    suspend fun getAllPost()= communityService.getAllPost().await()
    suspend fun getPostDetail(token: String,id: String)= communityService.getPostDetail(token,id).await()
    suspend fun getSubPost(token: String,email:String)= communityService.getSubPost(token,email).await()
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}