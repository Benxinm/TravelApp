package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.data.CommentWithHead
import com.benxinm.travelapp.data.askModel.AddCommentModel
import com.benxinm.travelapp.data.askModel.GetCommentModel
import com.benxinm.travelapp.data.responseModel.PostDetailModel
import com.benxinm.travelapp.logic.Repository
class DetailViewModel : ViewModel() {
    private val pageSize=10
    var isChecking by mutableStateOf(false)
    var inputText by mutableStateOf("")
    var detailModel:PostDetailModel?=PostDetailModel("benxinm","123","123",1111L,1,1,"1324561")
    val commentList = mutableStateListOf<CommentWithHead>()
    var text by mutableStateOf("")
    val urlList= mutableStateListOf<String>()
    private val _addCommentLiveData = MutableLiveData<AddCommentModel>()
    var target by mutableStateOf("")
    var targetNickname by mutableStateOf("")
    val addCommentLiveData = Transformations.switchMap(_addCommentLiveData) { addCommentModel ->
        Repository.addComment(
            addCommentModel.token,
            addCommentModel.userName,
            addCommentModel.type,
            addCommentModel.word,
            addCommentModel.target,
            addCommentModel.level
        )
    }
    fun addComment(token:String, username: String, type: Int, word: String, target: String, level: Int) {
        _addCommentLiveData.value = AddCommentModel(token,username, type, word, target, level)
    }

    private val _getCommentLiveData=MutableLiveData<GetCommentModel>()
    val getCommentLiveData=Transformations.switchMap(_getCommentLiveData){getCommentModel->
        Repository.getComments(getCommentModel.type,getCommentModel.target,getCommentModel.level,getCommentModel.page,getCommentModel.pageSize)
    }
    fun getComments(type: Int, target: String, level: Int,page:Int){
        _getCommentLiveData.value= GetCommentModel(type, target, level, page, pageSize)
    }
}