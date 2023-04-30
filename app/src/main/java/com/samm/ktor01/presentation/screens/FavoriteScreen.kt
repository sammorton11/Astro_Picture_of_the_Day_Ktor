package com.samm.ktor01.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.presentation.components.ResponseData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    favoritesList: List<Apod>,
    unFavorite: (Apod) -> Unit,
    insert: (Apod) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val favorites = favoritesList.reversed()
        val removeDuplicates = favorites.toSet()

        if (!favorites.isEmpty()) {
            items(favorites.size) { index ->

                val current = favorites[index]

                Log.d("Current", "${current.mediaType}")

                val date = current.date
                val explanation = current.explanation
                val hdurl = current.hdUrl
                val title = current.title
                val mediaType = current.mediaType

                Card(
                    modifier = Modifier.padding(15.dp)
                ) {
                    ResponseData(
                        title = title,
                        date = date,
                        explanation = explanation,
                        hdurl = hdurl,
                        unFavorite = { unFavorite(favoritesList[index]) },
                        apod = favoritesList[index],
                        favorite = insert,
                        onFavoriteScreen = true
                    )
                }
            }
        }
    }
}