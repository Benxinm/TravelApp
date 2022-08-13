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
    suspend fun getUrl(id:String)= communityService.getUrls(id).await()
    suspend fun getAllPost()= communityService.getAllPost().await()
    suspend fun getPostDetail(id: String)= communityService.getPostDetail(id).await()
    suspend fun getSubPost(email:String)= communityService.getSubPost(email).await()
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