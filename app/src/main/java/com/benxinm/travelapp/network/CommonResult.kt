package com.benxinm.travelapp.network

data class CommonResult<T>(val code:Int,val msg:String,val data:T,val map:Map<String,String>)