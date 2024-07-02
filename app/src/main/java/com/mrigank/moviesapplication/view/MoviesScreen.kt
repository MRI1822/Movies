package com.mrigank.moviesapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.mrigank.moviesapplication.model.Movie
import com.mrigank.moviesapplication.MoviesViewModel
import com.mrigank.moviesapplication.R
import com.mrigank.moviesapplication.State


@Composable
fun ShowMovies(
    moviesViewModel: MoviesViewModel
) {
    val moviesState by moviesViewModel.moviesState.collectAsState()
    when(moviesState) {
        is State.Success -> {
            DisplayMoviesSuccess(successState = moviesState as State.Success)
        }
        is State.Error -> {
            DisplayMoviesError(errorState = moviesState as State.Error)
        }
        is State.Empty -> {
            DisplayMoviesEmpty(emptyState = moviesState as State.Empty)
        }
        State.Loading -> {
            ShowProgressIndicator()
        }
    }
}


@Composable
fun ListMovieHeader(modifier: Modifier = Modifier) {
    Text(
        text = "Movies Playing Now",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(
                    id = R.dimen.vertical_padding
                ),
                horizontal = dimensionResource(
                    id = R.dimen.horizontal_padding
                )
            )
    )
}

@Composable
fun DisplayPagedMovies(movies: LazyPagingItems<Movie>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.list_item_padding)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.list_vertical_spacing)
        )
    ) {
        item {
            Row(
                Modifier.padding(dimensionResource(id = R.dimen.header_padding)),
                horizontalArrangement = Arrangement.Center
            ) {
                ListMovieHeader()
            }
        }


        items(count = movies.itemCount) { index ->
            MovieItem(movie = movies[index] ?: Movie("Not Available"))
        }
    }
}

@Composable
fun ShowProgressIndicator() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.progress_indicator_padding)),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = dimensionResource(id = R.dimen.box_border_width),
                color = Color.Gray,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_corner_radius))
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_corner_radius))
            )
            .padding(dimensionResource(id = R.dimen.box_inner_padding))
    ) {
        Column(
            modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.list_item_padding))
        ) {
            Text(text = movie.title)
        }
    }
}

@Composable
fun DisplayMoviesSuccess(successState: State.Success) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.list_item_padding)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.list_vertical_spacing)
        )
    ) {
        item {
            Row(
                Modifier.padding(dimensionResource(id = R.dimen.header_padding)),
                horizontalArrangement = Arrangement.Center
            ) {
                ListMovieHeader()
            }
        }
        items(successState.data.results) { movies ->
            MovieItem(movie = movies)
        }
    }
}

@Composable
fun DisplayMoviesError(errorState: State.Error) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorState.message)
    }
}

@Composable
fun DisplayMoviesEmpty(emptyState: State.Empty) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = emptyState.message)
    }
}
