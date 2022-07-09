package com.mobile.jetmovieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.jetmovieapp.models.Movie
import com.mobile.jetmovieapp.models.getMovieList
import com.mobile.jetmovieapp.navigation.MovieScreens
import com.mobile.jetmovieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent) {
            Text(text = "Movies", style = MaterialTheme.typography.h6)
        }
    }) {
        MainContent(navController)
    }
}

@Composable
fun MainContent(navController : NavController,movieList: List<Movie> = getMovieList()) {
    Column(modifier = Modifier.padding(10.dp)){
        LazyColumn{
            items(items = movieList){
                MovieRow(it){movie->
                    navController.navigate(MovieScreens.DetailScreen.name + "/$movie")
                }
            }
        }

    }
}
