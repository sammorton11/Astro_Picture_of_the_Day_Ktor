package com.samm.ktor01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.samm.ktor01.domain.Apod
import com.samm.ktor01.domain.Response
import com.samm.ktor01.presentation.AstroViewModel
import com.samm.ktor01.ui.theme.Ktor01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ktor01Theme {
                val viewModel: AstroViewModel = viewModel()
                val data = viewModel.responseFlow.collectAsStateWithLifecycle()
                val dataList = viewModel.responseFlowList.collectAsStateWithLifecycle()
                
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetAstroData(data = data, dataList = dataList)
                }
            }
        }
    }
}


@Composable
fun GetAstroData(data: State<Response?>, dataList:  State<List<Apod?>>) {

//    val date = data.value?.date
//    val explanation = data.value?.explanation
//    val hdurl = data.value?.hdUrl
//    val title = data.value?.title
//    val media_type = data.value?.mediaType
//    val service_version = data.value?.serviceVersion

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
    ) {
        dataList.value.size.let { amountOfItems ->
            items(amountOfItems) { index ->

                val date = dataList.value[index]?.date
                val explanation = dataList.value[index]?.explanation
                val hdurl = dataList.value[index]?.hdUrl
                val title = dataList.value[index]?.title

                title?.let { Text(text = it) }
                hdurl?.let { AsyncImage(model = it, contentDescription = "HD Image") }
                explanation?.let { Text(text = it) }
                date?.let { Text(text = it) }
            }
        }
    }
}