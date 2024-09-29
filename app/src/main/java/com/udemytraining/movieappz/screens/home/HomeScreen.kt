package com.udemytraining.movieappz.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.udemytraining.movieappz.model.Movie
import com.udemytraining.movieappz.model.getMovies
import com.udemytraining.movieappz.navigation.MovieScreens
import com.udemytraining.movieappz.widgets.MovieRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            title = {
                Text(
                    "Movies",
                    maxLines = 1,
                )
            }
        )
    }) { innerPadding ->
        MainContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MainContent(
    navController: NavController,
    modifier: Modifier = Modifier, movieList: List<Movie> =  getMovies()

) {
    Column(modifier = Modifier.padding(12.dp, 70.dp)) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}