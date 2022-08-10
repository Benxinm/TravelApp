package com.benxinm.travelapp.data

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("user_name") val email:String, @SerializedName("user_nick") val nickname:String)//TODO 可以删掉？
