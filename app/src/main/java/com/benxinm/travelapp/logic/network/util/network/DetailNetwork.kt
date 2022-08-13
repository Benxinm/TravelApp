package com.benxinm.travelapp.logic.network.util.network

import com.benxinm.travelapp.logic.network.service.DetailService
import com.benxinm.travelapp.logic.network.util.PythonServiceCreator
import com.benxinm.travelapp.logic.network.util.network.DetailNetwork.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object DetailNetwork {
    private val detailService=PythonServiceCreator.create(DetailService::class.java)
    suspend fun addLike(type:String,target:String)= detailService.addLike(type, target).await()
    suspend fun cancelLike(type:String,target:String)= detailService.cancelLike(type, target).await()
    suspend fun addComment(email:String,type:Int,text:String,target: String,level:Int)= detailService.addComment(email, type, text, target, level).await()
    suspend fun deleteComment(id:String)= detailService.deleteComment(id).await()
    suspend fun updateComment(id: String,email: String,type: Int,text: String,target: String,level: Int,likes:Int)=
        detailService.updateComment(id, email, type, text, target, level, likes).await()
    suspend fun getComments(type: Int,target: String,level: Int,page:Int,pageSize:Int)=
        detailService.getComments(type, target, level, page, pageSize).await()
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