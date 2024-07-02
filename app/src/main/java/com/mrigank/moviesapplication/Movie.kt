package com.mrigank.moviesapplication

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("original_title")
    val title: String
)