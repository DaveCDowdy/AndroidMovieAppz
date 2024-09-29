package com.udemytraining.movieappz.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.udemytraining.movieappz.model.Movie
import com.udemytraining.movieappz.model.getMovies

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {}) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier.padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                color = MaterialTheme.colorScheme.surfaceVariant
               ){

                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(enable = true)
                        .transformations(CircleCropTransformation())
                        .build(),
                )
                Image(painter = painter, contentDescription = "Movie Poster")
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title,
                    style = MaterialTheme.typography.headlineSmall)
                Text(text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium)

                AnimatedVisibility(visible = expanded) {
                    Column() {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize =13.sp,
                                fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        HorizontalDivider(modifier = Modifier.padding(5.dp))
                        Text(text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Icon(imageVector =  if (expanded) Icons.Filled.KeyboardArrowUp else
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier.size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray)
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .build(),
                )
                Image(
                    painter = painter, contentDescription = "Movie Images",
                    modifier = Modifier.fillParentMaxSize()
                )
            }
        }
    }
}