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
import com.samm.ktor01.core.Resource
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.presentation.components.ResponseData
import com.samm.ktor01.presentation.viewmodels.SingleItemScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SingleItemViewScreen(
    state : SingleItemScreenState?,
    insert: (Apod) -> Unit,
    unFavorite: (Apod) -> Unit
) {

    val data = state?.data
    
    val date = data?.date
    val explanation = data?.explanation
    val hdurl = data?.hdUrl
    val title = data?.title
    val media_type = data?.mediaType // Todo: use this to determine if we need a media player or async image

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            ResponseData(
                title = title,
                date = date,
                explanation = explanation,
                hdurl = hdurl,
                favorite = insert,
                unFavorite = unFavorite,
                apod = data,
                onFavoriteScreen = false
            )
        }

        item {
            OutlinedButton(
                onClick = {
                    data?.let {
                        insert(it)
                    }
                }
            ) {
                Text(text = "Save to Favorites")
            }
        }
    }
}