package com.samm.ktor01.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.samm.ktor01.domain.models.Apod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponseData(
    title: String?,
    date: String?,
    explanation: String?,
    hdurl: String?,
    favorite: (Apod) -> Unit,
    unFavorite: (Apod) -> Unit,
    apod: Apod?,
    onFavoriteScreen: Boolean
) {

    var favoriteIcon by remember { mutableStateOf(Icons.Filled.FavoriteBorder) }

    if (onFavoriteScreen) {
        favoriteIcon = Icons.Filled.Favorite
    }


    Column(modifier = Modifier.padding(15.dp)) {
        title?.let { Title(text = it) }
        hdurl?.let {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
            ) {
                Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    SubcomposeAsyncImage(
                        model = it,
                        contentDescription = "HD Image",
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(130.dp)
                            )
                        },
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {
                            when (favoriteIcon) {
                                Icons.Filled.Favorite -> {
                                    favoriteIcon = Icons.Filled.FavoriteBorder
                                    if (apod != null) {
                                        unFavorite(apod)
                                    }
                                }
                                Icons.Filled.FavoriteBorder -> {
                                    favoriteIcon = Icons.Filled.Favorite
                                    if (apod != null) {
                                        favorite(apod)
                                    }
                                }
                            }
                        }) {
                            Icon(imageVector = favoriteIcon, contentDescription = "Add or Remove to Favorites - Button")
                        }
                    }

                }

            }
        }
        explanation?.let { Text(text = it) }
        date?.let { Text(text = it) }
    }
}