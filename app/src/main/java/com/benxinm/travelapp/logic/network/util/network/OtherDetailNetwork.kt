package com.benxinm.travelapp.logic.network.util.network

import com.benxinm.travelapp.logic.network.service.OtherDetailService
import com.benxinm.travelapp.logic.network.util.PythonServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object OtherDetailNetwork {
    private val otherDetailService= PythonServiceCreator.create(OtherDetailService::class.java)
    suspend fun getIntroHis(target:String)= otherDetailService.getIntroHis(target).await()
    suspend fun getRecipe(target: String)= otherDetailService.getRecipe(target).await()
    suspend fun getRestaurant(target: String)= otherDetailService.getRestaurant(target).await()
    suspend fun getFood(address:String)= otherDetailService.getFood(address).await()
    suspend fun getRelatedSight(target: String)= otherDetailService.getRelatedSight(target).await()
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