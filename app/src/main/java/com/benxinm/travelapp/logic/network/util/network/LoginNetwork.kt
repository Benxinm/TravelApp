package com.benxinm.travelapp.logic.network.util.network

import com.benxinm.travelapp.logic.network.service.LoginService
import com.benxinm.travelapp.logic.network.util.PythonServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object LoginNetwork {
    private val loginService = PythonServiceCreator.create(LoginService::class.java)
    suspend fun login(username: String, password: String) =
        loginService.login(username, password).await()
    suspend fun register(username: String, nickname: String, password: String, rePassword: String) =
        loginService.register(username, nickname, password, rePassword).await()
    suspend fun sendEmail(email:String)= loginService.sendEmail(email).await()
    suspend fun verifyCode(code:String)= loginService.verifyCode(code).await()
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