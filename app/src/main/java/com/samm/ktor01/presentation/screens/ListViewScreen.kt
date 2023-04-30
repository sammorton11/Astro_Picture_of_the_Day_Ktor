package com.samm.ktor01.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.presentation.components.ResponseData
import com.samm.ktor01.presentation.viewmodels.ListViewScreenState
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewScreen(
    state: ListViewScreenState?,
    insert: (Apod) -> Unit,
    unFavorite: (Apod) -> Unit
) {
    
    var text by remember { mutableStateOf("") }
    val sortedItemsByName = state?.data?.filter { it?.title?.contains(text) == true }

    when {
        state?.isLoading == true -> {
            CircularProgressIndicator(
                modifier = Modifier
            )
        }
        state?.data?.isNotEmpty() == true -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(11.dp)
                    .padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        maxLines = 1,
                        placeholder = { Text("Search Results...") },
                        trailingIcon = {
                            IconButton(onClick = { text = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear Search Field"
                                )
                            }
                        }
                    )
                }

                items(sortedItemsByName?.size ?: 0) { index ->

                    val current = sortedItemsByName?.get(index)

                    Log.d("Current", "${current?.mediaType}")

                    val date = current?.date
                    val explanation = current?.explanation
                    val hdurl = current?.hdUrl
                    val title = current?.title
                    val mediaType = current?.mediaType

                    Card(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        ResponseData(
                            title = title,
                            date = date,
                            explanation = explanation,
                            hdurl = hdurl,
                            unFavorite = unFavorite,
                            apod = current,
                            favorite = insert,
                            onFavoriteScreen = false
                        )
                    }
                }
            }
        }
        state?.error?.isNotEmpty() == true -> {
            Text(text = state.error)
        }
    }
}