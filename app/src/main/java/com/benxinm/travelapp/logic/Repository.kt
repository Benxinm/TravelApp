package com.benxinm.travelapp.logic

import androidx.lifecycle.liveData
import com.benxinm.travelapp.data.User
import com.benxinm.travelapp.logic.network.util.network.LoginNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun login(username:String,password:String)= liveData(Dispatchers.IO){
        val result=try {
            val loginResponse = LoginNetwork.login(username, password)
            if (loginResponse.code==200){
                val user=loginResponse.data
                Result.success(user)
            }else{
                Result.failure(RuntimeException("response status is${loginResponse.message}"))
            }
        }catch (e:Exception){
            Result.failure<User>(e)
        }
        emit(result)
    }
    fun register(username: String,nickname:String,password: String,rePassword:String)= liveData(Dispatchers.IO) {
        val result=try {
            val registerResponse=LoginNetwork.register(username, nickname, password, rePassword)
            if (registerResponse.code==200){
                val user=registerResponse.data
                Result.success(user)
            }else{
                Result.failure(RuntimeException("response status is${registerResponse.message}"))

            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
}