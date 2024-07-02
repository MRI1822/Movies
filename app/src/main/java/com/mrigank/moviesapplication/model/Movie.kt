package com.mrigank.moviesapplication.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("original_title")
    val title: String
)