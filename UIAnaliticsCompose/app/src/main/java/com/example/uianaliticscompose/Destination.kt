package com.example.uianaliticscompose

import java.io.Serializable

data class Destination(
    val id:Int,
    var name:String,
    var shortDescription:String,
    var description:String,
    var country:String,
    val imageRes:Int,
    var isVisited:Boolean
): Serializable

