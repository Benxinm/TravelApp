package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.logic.Repository

class UserViewModel:ViewModel() {
    val defaultPortrait="https://s2.loli.net/2022/08/03/2W9Nmf1SBpoRFdi.jpg"
    var profile by mutableStateOf("")
    var token by mutableStateOf("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiNTk2NjIiLCJyb2xlIjoiY3VzdG9tZXIifQ.6VBRw3jWOp31YPeOt1MIpCvLWzZQCw4sP8ZghDwmK_s")
    var tokenTest by mutableStateOf("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiMTA3ODcxMzUwN0BxcS5jb20iLCJyb2xlIjoiY3VzdG9tZXIifQ.Ss-CKi2WMopQUIZn5S9nB1HmYbXr8WAdyJUFhruIBQc")
    var email by mutableStateOf("")
    var emailTest by mutableStateOf("1078713507@qq.com")
    var nickname by mutableStateOf("")
    var userProfile by mutableStateOf(defaultPortrait)
    var targetEmail by mutableStateOf("")
    val degrees = mutableStateListOf(0f,0f,0f,0f,0f)
    var targetEmailForDetail by mutableStateOf("")
    var fanNum by mutableStateOf(0)
    var subNum by mutableStateOf(0)
    private val getLiveData = MutableLiveData<String>()
    val fanSubNumLiveData=Transformations.switchMap(getLiveData){email->
        Repository.getFanSubNum(email)
    }
    fun getFanSubNum(email:String){
        getLiveData.value=email
    }
}