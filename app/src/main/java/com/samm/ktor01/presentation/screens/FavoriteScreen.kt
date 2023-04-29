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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.presentation.components.ResponseData
import com.samm.ktor01.presentation.viewmodels.AstroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(viewModel: AstroViewModel, unFavorite: (Apod) -> Unit, insert: (Apod) -> Unit) {

    val state = viewModel
        .getAllFavorites()
        .collectAsStateWithLifecycle(
            initialValue = listOf(
                Apod(
                    copyright = "copyright",
                    date = "date",
                    explanation= "explanation",
                    hdUrl = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg",
                    mediaType = "image",
                    serviceVersion = "v1",
                    title = "Title",
                    url = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg"
                )
            )
        )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val favorites = state.value.reversed()
        val removeDuplicates = favorites.toSet()

         items(removeDuplicates.size) { index ->

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
                    state = state,
                    unFavorite = { unFavorite(state.value[index]) },
                    apod = state.value[index],
                    favorite = insert,
                    onFavoriteScreen = true
                )
            }
        }
    }
}