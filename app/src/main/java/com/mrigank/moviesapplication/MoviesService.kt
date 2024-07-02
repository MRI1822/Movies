package com.mrigank.moviesapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {

    @GET("now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<Movies>
}