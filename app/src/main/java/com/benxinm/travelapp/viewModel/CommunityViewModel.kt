package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.data.TwoParamsModel
import com.benxinm.travelapp.data.responseModel.PostModel
import com.benxinm.travelapp.logic.Repository

class CommunityViewModel:ViewModel() {
    val postList= mutableStateListOf<PostModel>()
    fun getAllPost()=Repository.getAllPost()
    private val _getUrlsLivedata=MutableLiveData<TwoParamsModel>()
    val getUrlsLiveData=Transformations.switchMap(_getUrlsLivedata){model->
        Repository.getUrls(model.first,model.second)
    }
    fun getUrls(token:String,id:String){
        _getUrlsLivedata.value=TwoParamsModel(token,id)
    }
    private val _getPostDetailLivedata=MutableLiveData<TwoParamsModel>()
    val getPostDetailLivedata=Transformations.switchMap(_getPostDetailLivedata){model->
        Repository.getPostDetail(model.first,model.second)
    }
    fun getPostDetail(token: String,id:String){
        _getPostDetailLivedata.value=TwoParamsModel(token,id)
    }
    private val _getSubPostLivedata=MutableLiveData<TwoParamsModel>()
    val getSubPostLivedata=Transformations.switchMap(_getSubPostLivedata){model->
        Repository.getSubPost(model.first,model.second)
    }
    fun getSubPost(token: String,email:String){
        _getSubPostLivedata.value=TwoParamsModel(token,email)
    }
}