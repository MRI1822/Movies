package com.mrigank.moviesapplication

import com.mrigank.moviesapplication.model.Movies
sealed class State {
    data class Success(val data: Movies) : State()
    data class Error(val message: String) : State()
    data class Empty(val message: String) : State()
    object Loading : State()
}