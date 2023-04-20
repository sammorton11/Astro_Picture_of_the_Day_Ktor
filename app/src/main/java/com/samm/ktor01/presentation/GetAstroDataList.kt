package com.samm.ktor01.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAstroDataList(
    viewModel: AstroViewModel,
    count: Int = 50
) {
    val dataList = viewModel.responseFlowList.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        dataList.value.size.let { amountOfItems ->

            items(amountOfItems) { index ->

                val copyright = dataList.value[index]?.copyright
                val date = dataList.value[index]?.date
                val explanation = dataList.value[index]?.explanation
                val hdurl = dataList.value[index]?.hdUrl
                val title = dataList.value[index]?.title
                val mediaType = dataList.value[index]?.mediaType

                if (mediaType == "video") Log.d("Video here:", dataList.toString())

                Card(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        title?.let { Text(text = it) }
                        hdurl?.let {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                            ) {
                                AsyncImage(
                                    model = it,
                                    contentDescription = "HD Image",
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
}