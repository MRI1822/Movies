package com.mrigank.moviesapplication

import android.util.Log
import android.util.MalformedJsonException
import com.mrigank.moviesapplication.Constants.API_KEY
import com.mrigank.moviesapplication.Constants.LANGUAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val moviesService: MoviesService
): Repository {
    override fun fetchMovies() = flow<State> {
        Log.d("Repository Impl", "Hello")
        try {
            val response = moviesService.getMovies(
                apiKey = API_KEY,
                page = 1,
                language = LANGUAGE
            )
            Log.d("Repository Impl", response.toString())
            if(response.isSuccessful) {
                response.body()?.let {  movies ->
                    if(movies.results.isEmpty()) {
                        emit(State.Empty("Sorry we can't fetch the movies right now"))
                    } else {
                        emit(State.Success(data = movies))
                    }
                }
            } else {
                emit(State.Error("Sorry there's a network issue right now"))
            }
        } catch (e: MalformedJsonException) {
            emit(State.Error("Sorry, we don't have the right data available"))
        } catch (e: HttpException) {
            emit(State.Error("Sorry, there's a network issue"))
        } catch (e: Exception) {
            emit(State.Error("Sorry, there's an issue: ${e.message.toString()}"))
        }
    }.flowOn(Dispatchers.IO)

}