package com.samm.ktor01

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.samm.ktor01.presentation.AstroViewModel
import com.samm.ktor01.presentation.components.BottomNavigation
import com.samm.ktor01.presentation.navigation.AppNavigation
import com.samm.ktor01.ui.theme.Ktor01Theme
import java.util.*

/**
 *      Todo:
 *          - Conditional for media types - image, and video - Gif?
 *          - Clean up
 *          - Component alignments and isolations
 *          - Restrict future date selection in date picker
 *          - Integration tests
 *          - Unit tests
 */

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: AstroViewModel = viewModel()
            viewModel.getSingleItemData()
            viewModel.getDataListByCount(10)

            Ktor01Theme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        BottomNavigation(onTabSelected = {
                            // Navigate to the selected screen using a navController
                            when (it) {
                                0 -> { navController.navigate("screen1") }
                                1 -> { navController.navigate("screen2") }
                                2 -> { navController.navigate("screen3") }
                            }
                        })
                    }, content = {
                        AppNavigation(navController)
                    })
                }
            }
        }
    }
}