package com.samm.ktor01.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.samm.ktor01.presentation.components.Title
import com.samm.ktor01.presentation.viewmodels.ListViewScreenState
import kotlinx.coroutines.flow.StateFlow
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewScreen(state: StateFlow<ListViewScreenState?>) {

    val dataList = state.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf("") }

    val sortedItemsByName = dataList.value?.data?.filter { it?.title?.contains(text) == true }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            OutlinedTextField(value = text, onValueChange = {
                text = it
            })
        }

        items(sortedItemsByName?.size ?: 0) { index ->

            val current = sortedItemsByName?.get(index)

            Log.d("Current", "${current?.mediaType}")

            val copyright = current?.copyright
            val date = current?.date
            val explanation = current?.explanation
            val hdurl = current?.hdUrl
            val title = current?.title
            val mediaType = current?.mediaType

            if (mediaType == "video") Log.d("Video here:", dataList.toString())

            Card(
                modifier = Modifier.padding(15.dp)
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    title?.let { Title(text = it) }
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
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
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