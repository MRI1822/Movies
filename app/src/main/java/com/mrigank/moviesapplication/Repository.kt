package com.mrigank.moviesapplication

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchMovies(): Flow<State>
}