package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.data.User
import com.benxinm.travelapp.logic.Repository
import kotlin.math.log

class LoginViewModel : ViewModel() {
    private val loginLiveData = MutableLiveData<HashMap<String,String>>()
    val userLiveData=Transformations.switchMap(loginLiveData){map->
        Repository.login(map["email"]!!,map["password"]!!)
    }
    fun login(email:String,password:String){
        val map=HashMap<String,String>()
        map["email"] = email
        map["password"] = password
        loginLiveData.value=map
    }

    private val registerLiveData=MutableLiveData<HashMap<String,String>>()
    val registerUserLiveData=Transformations.switchMap(registerLiveData){map->
        Repository.register(map["email"]!!,map["nickname"]!!,map["password"]!!,map["rePassword"]!!)
    }
    fun register(email: String,nickname:String,password: String,rePassword:String){
        val map=HashMap<String,String>()
        map["email"] = email
        map["nickname"] = nickname
        map["password"] = password
        map["rePassword"] = rePassword
        registerLiveData.value=map
    }
    fun sendEmail(email:String)=Repository.sendEmail(email).value?.isSuccess
    fun verifyEmail(code:String)=Repository.verifyCode(code).value?.isSuccess
}