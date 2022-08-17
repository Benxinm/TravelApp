package com.benxinm.travelapp.logic.network.util.network

import com.benxinm.travelapp.logic.network.service.CollectService
import com.benxinm.travelapp.logic.network.service.DetailService
import com.benxinm.travelapp.logic.network.util.PythonServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Path
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CollectNetwork {
    private val collectService = PythonServiceCreator.create(CollectService::class.java)
    suspend fun addCollect(
        token: String,
        email: String,
        type: Int,
        target: String,
        firstUrl: String
    ) = collectService.addCollect(token, email, type, target, firstUrl).await()
    suspend fun deleteCollect(
        token: String,
        id: String
    ) = collectService.deleteCollect(token, id).await()
    suspend fun updateCollect(token: String, email: String,type: Int,target: String, firstUrl: String)=
        collectService.updateCollect(token,email, type, target, firstUrl).await()
    suspend fun getCollects(token: String,email: String,type: Int,page: Int,pageSize: Int)=
        collectService.getCollects(token,email, type, page, pageSize).await()
    suspend fun getCollectCount(token: String,target: String)= collectService.getCollectCount(token,target).await()
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