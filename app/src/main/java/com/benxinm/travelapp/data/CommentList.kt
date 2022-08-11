package com.benxinm.travelapp.data

data class CommentList(
    val records: List<Comment>,
    val total: Int,
    val size: Int,
    val current: Int,
    val orders: List<String>,
    val optimizeCountSql:Boolean,
    val searchCount:Boolean,
    val countId:String?,
    val maxLimit:String?,
    val pages:Int
)
