package com.theapplication.apitest.Screens

import android.annotation.SuppressLint
import android.provider.ContactsContract.Data
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.theapplication.apitest.R
import com.theapplication.apitest.navigation.Route
import com.theapplication.apitest.viewmodels.MovieViewModel
import org.w3c.dom.Text
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val movieViewModel = viewModel<MovieViewModel>()
    val state = movieViewModel.state
    var searchText by remember { mutableStateOf("") }

    val linearGradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFB226E1),
            Color(0xFFE64514),
            Color(0xFF1565C0),
            Color(0xFF3B3A3C)

        ),
        start = Offset(Float.POSITIVE_INFINITY,0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Movies Point") }, colors = TopAppBarDefaults.topAppBarColors(
                Color.Transparent
            )
        )
    }, containerColor = Color.Transparent) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(value = searchText, onValueChange ={searchText=it},
                    placeholder = { Text(text = "Search Movie")},
                    leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription =null )},
                    modifier = Modifier.clip(shape = RoundedCornerShape(25.dp)).border(4.dp,linearGradientBrush,
                        RoundedCornerShape(25.dp)
                    ).width(300.dp))

            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
                    .background(Color.Transparent),
                content = {
                    val filteredMovies = if (searchText.isNotBlank()) {
                        state.movies.filter {
                            it.title.contains(searchText, ignoreCase = true)
                        }
                    } else {
                        state.movies
                    }
                    items(filteredMovies.size) {
                        ItemUi(itemIndex = it, movieList = filteredMovies, navController)
                    }
                }

            )
        }

    }
}

@Composable
fun ItemUi(itemIndex: Int, movieList: List<com.theapplication.apitest.models.Data>,navController: NavController) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable {
                navController.navigate("Route.DetailsScreen.route/${movieList[itemIndex].id}")
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(
                model = movieList[itemIndex].poster,
                contentDescription = movieList[itemIndex].title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.LightGray.copy(.7f))
                    .padding(6.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = movieList[itemIndex].title,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color(0xFFFC5A03),
                            offset = Offset(1f, 1f), 3f
                        ),
                    ),
                    maxLines = 1,

                    )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(imageVector = Icons.Rounded.Star, contentDescription = null)
                    Text(
                        text = movieList[itemIndex].imdb_rating,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 2
                    )
                }
            }
        }
    }
}


