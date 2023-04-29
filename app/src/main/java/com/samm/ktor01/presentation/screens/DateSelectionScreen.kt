package com.samm.ktor01.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samm.ktor01.presentation.components.MyDatePicker
import com.samm.ktor01.presentation.components.ResponseData
import com.samm.ktor01.presentation.viewmodels.DateSelectionScreenState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DateSelectionScreen(
    state: StateFlow<DateSelectionScreenState?>,
    getData: (String) -> Unit,
) {

    val data = state.collectAsStateWithLifecycle()

    val date = data.value?.data?.date
    val explanation = data.value?.data?.explanation
    val hdurl = data.value?.data?.hdUrl
    val title = data.value?.data?.title
    val media_type = data.value?.data?.mediaType

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
                    hdurl = hdurl
                )
            }
        }

        item {
            Box(contentAlignment = Alignment.BottomCenter) {
                MyDatePicker(getData = getData)
            }
        }
    }
}