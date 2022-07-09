package com.mobile.jetmovieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.mobile.jetmovieapp.models.Movie


@Composable
fun MovieRow(movie : Movie, onItemClicked : (String) -> Unit = {}) {
     var expanded by remember{
         mutableStateOf(false)
     }

    Card(modifier = Modifier
        .padding(6.dp)
        .fillMaxWidth()
        .clickable { onItemClicked(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {

        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {

            Surface(modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
                shape = CircleShape,
                elevation = 5.dp) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images.first())
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "Image"
                )
            }

            Column(modifier = Modifier
                .padding(top = 5.dp)
                .width(150.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6 )
                Text(text = "Released : ${movie.year}", style = MaterialTheme.typography.caption )

                AnimatedVisibility(visible = expanded) {
                    Column(modifier = Modifier.padding(top = 5.dp)) {

                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Genre : ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp,fontWeight = FontWeight.Bold)){
                                append(movie.genre)
                            }
                        })

                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Ratings : ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp,fontWeight = FontWeight.Bold)){
                                append(movie.rating)
                            }
                        })
                    }
                }
            }


            Icon(modifier = Modifier
                .size(25.dp)
                .clickable { expanded = !expanded },
                imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Arrow")


        }

    }
}