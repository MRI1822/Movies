package com.mrigank.moviesapplication.repository

import com.mrigank.moviesapplication.State
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchMovies(): Flow<State>
}