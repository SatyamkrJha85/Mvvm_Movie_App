package com.theapplication.apitest.models

data class Data(
    val country:String,
    val genres: List<String>, // Change type to List<String>
    val id:Int,
    val images : List<String>,
    val imdb_rating:String,
    val poster:String,
    val title:String,
    val year:String
)