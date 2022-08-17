package com.benxinm.travelapp.data.responseModel

import com.google.gson.annotations.SerializedName

data class CollectModel(val id:String, @SerializedName("userName") val email:String, val type:Int, val target:String, val time:Long )
