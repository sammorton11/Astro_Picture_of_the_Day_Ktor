package com.samm.ktor01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.samm.ktor01.ui.theme.Ktor01Theme
import io.ktor.http.cio.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ktor01Theme {
                val viewModel: AstroViewModel = viewModel()
                val data = viewModel.responseFlow.collectAsState()
                
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetAstroData(data = data)
                }
            }
        }
    }
}


@Composable
fun GetAstroData(data: State<Response?>) {

    val date = data.value?.date
    val url = data.value?.url
    val explanation = data.value?.explanation
    val hdurl = data.value?.hdUrl
    val title = data.value?.title
    val media_type = data.value?.mediaType
    val service_version = data.value?.serviceVersion

    Column {
        hdurl?.let {
            AsyncImage(model = it, contentDescription = "HD Image")
        }
        explanation?.let { Text(text = it) }
        title?.let { Text(text = it) }
        media_type?.let { Text(text = it) }
        service_version?.let { Text(text = it) }
        date?.let { Text(text = it) }
    }
}