package com.mrigank.moviesapplication

import androidx.lifecycle.ViewModel
import com.mrigank.moviesapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var _moviesState: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val moviesState: StateFlow<State> = _moviesState

    suspend fun getMovies() {
        repository.fetchMovies().collectLatest { state ->
            _moviesState.value = state
        }
    }
}