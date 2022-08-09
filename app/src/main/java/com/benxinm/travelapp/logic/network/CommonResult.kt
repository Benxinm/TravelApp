package com.benxinm.travelapp.logic.network

data class CommonResult<T>(val code:Int,val msg:String,val data:T,val map:Map<String,String>)