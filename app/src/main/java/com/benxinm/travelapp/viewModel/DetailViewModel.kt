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
import com.benxinm.travelapp.logic.Repository
const val pageSize=10
class DetailViewModel : ViewModel() {
    var inputText by mutableStateOf("")
    val commentList = mutableStateListOf<CommentWithHead>()
    private val _addCommentLiveData = MutableLiveData<AddCommentModel>()
    val addCommentLiveData = Transformations.switchMap(_addCommentLiveData) { addCommentModel ->
        Repository.addComment(
            addCommentModel.userName,
            addCommentModel.type,
            addCommentModel.word,
            addCommentModel.target,
            addCommentModel.level
        )
    }
    fun addComment(username: String, type: Int, word: String, target: String, level: Int) {
        _addCommentLiveData.value = AddCommentModel(username, type, word, target, level)
    }

    private val _getCommentLiveData=MutableLiveData<GetCommentModel>()
    val getCommentLiveData=Transformations.switchMap(_getCommentLiveData){getCommentModel->
        Repository.getComments(getCommentModel.type,getCommentModel.target,getCommentModel.level,getCommentModel.page,getCommentModel.pageSize)
    }
    fun getComments(type: Int, target: String, level: Int,page:Int){
        _getCommentLiveData.value= GetCommentModel(type, target, level, page, pageSize)
    }
}