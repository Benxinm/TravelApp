package com.benxinm.travelapp.logic.network.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PythonServiceCreator {
    //    private const val BASE_URL = "http://10.0.2.2:1111/"
    private const val BASE_URL = "http://fdtp.vipgz4.91tunnel.com/"
    private val httpClient = OkHttpClient.Builder().callTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS).readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}