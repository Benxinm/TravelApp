package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.data.responseModel.PostModel
import com.benxinm.travelapp.logic.Repository

class CommunityViewModel:ViewModel() {
    val postList= mutableStateListOf<PostModel>()
    fun getAllPost()=Repository.getAllPost()
    private val _getUrlsLivedata=MutableLiveData<String>()
    val getUrlsLiveData=Transformations.switchMap(_getUrlsLivedata){id->
        Repository.getUrls(id)
    }
    fun getUrls(id:String){
        _getUrlsLivedata.value=id
    }
    private val _getPostDetailLivedata=MutableLiveData<String>()
    val getPostDetailLivedata=Transformations.switchMap(_getUrlsLivedata){id->
        Repository.getPostDetail(id)
    }
    fun getPostDetail(id:String){
        _getPostDetailLivedata.value=id
    }
    private val _getSubPostLivedata=MutableLiveData<String>()
    val getSubPostLivedata=Transformations.switchMap(_getUrlsLivedata){email->
        Repository.getSubPost(email)
    }
    fun getSubPost(email:String){
        _getSubPostLivedata.value=email
    }
}