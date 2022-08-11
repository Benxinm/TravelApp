package com.benxinm.travelapp.logic.network.util.network

import com.benxinm.travelapp.logic.network.service.LoginService
import com.benxinm.travelapp.logic.network.service.UserService
import com.benxinm.travelapp.logic.network.util.PythonServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object UserNetwork {
    private val userService = PythonServiceCreator.create(UserService::class.java)
    suspend fun getFanSubNum(email:String)= userService.getFanSubNum(email).await()
    suspend fun changeNickname(email: String,newNickname:String)= userService.changeNickname(email, newNickname).await()//TODO 后续的还没写
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