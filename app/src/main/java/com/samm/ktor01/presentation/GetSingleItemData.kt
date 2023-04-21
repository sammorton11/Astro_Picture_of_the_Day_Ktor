package com.samm.ktor01.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetSingleItemData(viewModel: AstroViewModel) {

    val data = viewModel.responseFlow.collectAsStateWithLifecycle()
    val date = data.value?.date
    val explanation = data.value?.explanation
    val hdurl = data.value?.hdUrl
    val title = data.value?.title
    val media_type = data.value?.mediaType // Todo: use this to determine if we need a media player or async image

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
                Column(modifier = Modifier.padding(15.dp)) {
                    title?.let { Text(text = it) }
                    hdurl?.let {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
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
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    explanation?.let { Text(text = it) }
                    date?.let { Text(text = it) }
                }
            }
        }
    }
}