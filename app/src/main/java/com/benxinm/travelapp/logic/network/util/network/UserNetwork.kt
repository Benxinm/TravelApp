package com.benxinm.travelapp.logic.network.util.network

import com.benxinm.travelapp.logic.network.service.LoginService
import com.benxinm.travelapp.logic.network.service.UserService
import com.benxinm.travelapp.logic.network.util.PythonServiceCreator
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object UserNetwork {
    private val userService = PythonServiceCreator.create(UserService::class.java)
    suspend fun getFanSubNum(email:String)= userService.getFanSubNum(email).await()
    suspend fun changeNickname(token: String,email: String,newNickname:String)= userService.changeNickname(token,email, newNickname).await()//TODO 后续的还没写
    suspend fun changePassword(token: String,email: String,password1:String,password2: String)=
        userService.changePassword(token,email, password1, password2).await()
    suspend fun uploadImage(token:String,email: String,file: MultipartBody.Part)= userService.uploadImage(token,email, file).await()
    suspend fun getFoodCollect(email: String)= userService.getMyFoodCollect(email).await()
    suspend fun getStoreCollect(email: String)= userService.getMyStoreCollect(email).await()
    suspend fun follow(token: String,email: String,target:String)= userService.follow(token,email, target).await()
    suspend fun setBottles(token: String,email: String,type:String,degree:String)=
        userService.setBottle(token, email, type, degree).await()
    suspend fun checkBottle(token: String,email: String)= userService.checkBottle(token, email).await()
    suspend fun getMyPost(token: String,email: String)= userService.getMyPost(token, email).await()
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