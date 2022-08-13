package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.data.responseModel.PostModel
import com.benxinm.travelapp.logic.Repository

class CommunityViewModel:ViewModel() {
    val postList= mutableStateListOf<PostModel>()
    fun getAllPost()=Repository.getAllPost()
}