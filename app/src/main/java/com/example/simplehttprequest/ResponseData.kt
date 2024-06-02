package com.example.simplehttprequest

data class ResponseData(
    val message:String,
    val name:String,
    val time:String,
    val items:List<ItemListDetail>,
    val x:XDetail,
    val y:XDetail
)

data class XDetail(
    val date:Int,
    val month:String
)

data class ItemListDetail(
    val id:Int,
    val name:String
)