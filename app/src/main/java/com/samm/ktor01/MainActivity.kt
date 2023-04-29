package com.samm.ktor01

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.samm.ktor01.core.UIEvent
import com.samm.ktor01.di.appModule
import com.samm.ktor01.di.viewModelModule
import com.samm.ktor01.presentation.components.BottomNavigation
import com.samm.ktor01.presentation.navigation.AppNavigation
import com.samm.ktor01.presentation.viewmodels.AstroViewModel
import com.samm.ktor01.ui.theme.Ktor01Theme
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 *      Todo:
 *          - Conditional for media types - image, and video - Gif?
 *          - Clean up
 *          - Component alignments and isolations
 *          - Integration tests
 *          - Unit tests
 */

class MainActivity : ComponentActivity(), KoinComponent {

    private val viewModel: AstroViewModel by inject()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule, viewModelModule)
        }

        setContent {

            val currentDate = LocalDate.of(2022, 1, 1)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = currentDate.format(formatter)

            viewModel.sendEvent(UIEvent.GetSingleItemData)
            viewModel.sendEvent(UIEvent.GetListItemsData(100))
            viewModel.sendEvent(UIEvent.GetDataByDate(formattedDate))


            Ktor01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    Scaffold(bottomBar = {
                        BottomNavigation(onTabSelected = {
                            when (it) {
                                0 -> { navController.navigate("screen1") }
                                1 -> { navController.navigate("screen2") }
                                2 -> { navController.navigate("screen3") }
                                3 -> { navController.navigate("screen4") }
                            }
                        })
                    }, content = {
                        Column(modifier = Modifier.fillMaxSize()) {
                            AppNavigation(navController, viewModel)
                        }
                    })
                }
            }
        }
    }
}