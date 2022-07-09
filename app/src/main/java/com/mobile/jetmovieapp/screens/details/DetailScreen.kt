package com.mobile.jetmovieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.mobile.jetmovieapp.models.Movie
import com.mobile.jetmovieapp.models.getMovieList
import com.mobile.jetmovieapp.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieID: String?){

    val newMovieList = getMovieList().filter {movie ->
        movie.id == movieID
    }

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.clickable { navController.popBackStack() },
            backgroundColor = Color.Transparent) {
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically){

                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                Spacer(modifier = Modifier.width(60.dp))
                Text(text = "Movies", style = MaterialTheme.typography.h6)
            }
        }
    }) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            MovieRow(movie = newMovieList.first())
            Spacer(modifier = Modifier.height(20.dp))
            Divider(modifier = Modifier.padding(10.dp))
            Text(text = "More Images", modifier = Modifier.padding(10.dp))
            HorizontalMovieImages(newMovieList)

        }
    }


}

@Composable
private fun HorizontalMovieImages(newMovieList: List<Movie>) {
    LazyRow(modifier = Modifier.padding(bottom = 30.dp)){
        items(newMovieList.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(300.dp), elevation = 6.dp
            ) {
                Image(painter = rememberImagePainter(data = image), contentDescription = "Images",contentScale = ContentScale.Crop)
            }

        }
    }
}