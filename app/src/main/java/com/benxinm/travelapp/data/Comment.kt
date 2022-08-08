package com.benxinm.travelapp.data

data class Comment(
    val id: String,
    val userName: String,
    val type: String,
    val word: String,
    val target: String,
    val level: Int,
    val grade:Int,
    val time:Long//TODO 看能不能自动转成Long
)
