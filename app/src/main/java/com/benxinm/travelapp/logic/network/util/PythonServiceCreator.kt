package com.benxinm.travelapp.logic.network.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PythonServiceCreator {
    private const val BASE_URL = "http://10.0.2.2:8888/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}