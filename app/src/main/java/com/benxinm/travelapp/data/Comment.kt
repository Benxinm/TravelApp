package com.benxinm.travelapp.data

data class Comment(
    val id: String,
    val userName: String,
    val type: String,
    val word: String,
    val target: String,
    val level: Int,
    val grade:Int,
    val time:Long,
    val head:String
)

data class CommentWithHead(
    val id: String,
    val userName: String,
    val type: String,
    val word: String,
    val target: String,
    val level: Int,
    val grade:Int,
    val time:Long,
    val head:String
)
