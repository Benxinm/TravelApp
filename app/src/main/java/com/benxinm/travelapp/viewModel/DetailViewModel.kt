package com.benxinm.travelapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.data.CommentWithHead

class DetailViewModel:ViewModel() {
    var inputText by mutableStateOf("")
    val commentList= mutableStateListOf<CommentWithHead>()
}