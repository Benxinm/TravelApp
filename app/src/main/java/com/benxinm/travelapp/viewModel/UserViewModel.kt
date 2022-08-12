package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.logic.Repository

class UserViewModel:ViewModel() {
    val defaultPortrait="https://s2.loli.net/2022/08/03/2W9Nmf1SBpoRFdi.jpg"
    var email by mutableStateOf("")
    var nickname by mutableStateOf("")
    var targetEmail by mutableStateOf("")
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