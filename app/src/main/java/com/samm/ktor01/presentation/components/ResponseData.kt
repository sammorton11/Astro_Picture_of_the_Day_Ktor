package com.samm.ktor01.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponseData(
    title: String?,
    date: String?,
    explanation: String?,
    hdurl: String?
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