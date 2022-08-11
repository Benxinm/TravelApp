package com.benxinm.travelapp.logic

import androidx.lifecycle.liveData
import com.benxinm.travelapp.data.User
import com.benxinm.travelapp.logic.network.util.network.DetailNetwork
import com.benxinm.travelapp.logic.network.util.network.LoginNetwork
import com.benxinm.travelapp.logic.network.util.network.UserNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    /**
     * Login
     */
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
            Result.failure(e)
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
    fun sendEmail(email:String)=liveData(Dispatchers.IO){
        val result=try {
            val sendEmailResponse=LoginNetwork.sendEmail(email)
            if (sendEmailResponse.code==200){
                Result.success(true)
            }else{
                Result.failure(RuntimeException("response status is${sendEmailResponse.message}"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
    fun verifyCode(code:String)=liveData(Dispatchers.IO){
        val result=try {
            val codeResponse=LoginNetwork.verifyCode(code)
            if (codeResponse.code==200){
                Result.success(true)
            }else{
                Result.failure(RuntimeException("response status is${codeResponse.message}"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
    /**
     * User
     */
    fun getFanSubNum(email: String)= liveData(Dispatchers.IO){
        val result=try {
            val numResponse=UserNetwork.getFanSubNum(email)
            if (numResponse.code==200){
                Result.success(numResponse.data)
            }else{
                Result.failure(RuntimeException("response status is${numResponse.message}"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
    /**
     * Detail
     */
    fun addLike(type:String,target:String)= liveData(Dispatchers.IO){
        val result=try {
            val response=DetailNetwork.addLike(type, target)
            if (response.code==1){
                Result.success(response.data)
            }else{
                Result.failure(RuntimeException("response status is${response.msg}"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
    fun cancelLike(type:String,target:String)= liveData(Dispatchers.IO){
        val result=try {
            val response=DetailNetwork.cancelLike(type, target)
            if (response.code==200){
                Result.success(response.data)
            }else{
                Result.failure(RuntimeException("response status is${response.msg}"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
}