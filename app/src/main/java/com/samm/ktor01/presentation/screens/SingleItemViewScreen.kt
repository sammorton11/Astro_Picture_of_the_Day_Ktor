package com.samm.ktor01.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.presentation.components.ResponseData
import com.samm.ktor01.presentation.viewmodels.SingleItemScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SingleItemViewScreen(
    state: StateFlow<SingleItemScreenState?>,
    insert: (Apod) -> Unit,
    unFavorite: (Apod) -> Unit,
    favorites: Flow<List<Apod>>
) {

    val faveoriteState = favorites.collectAsStateWithLifecycle(
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

    val data = state.collectAsStateWithLifecycle()
    val date = data.value?.data?.date
    val explanation = data.value?.data?.explanation
    val hdurl = data.value?.data?.hdUrl
    val title = data.value?.data?.title
    val media_type = data.value?.data?.mediaType // Todo: use this to determine if we need a media player or async image

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        data.value?.let {
            item {
                ResponseData(
                    title = title,
                    date = date,
                    explanation = explanation,
                    hdurl = hdurl,
                    favorite = insert,
                    unFavorite = unFavorite,
                    apod = data.value?.data,
                    onFavoriteScreen = false
                )
            }
        }

        item {
            OutlinedButton(
                onClick = {
                    data.value?.data?.let {
                        insert(it)
                    }
                }
            ) {
                Text(text = "Save to Favorites")
            }
        }
    }
}