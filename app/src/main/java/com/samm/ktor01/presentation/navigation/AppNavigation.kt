package com.samm.ktor01.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samm.ktor01.presentation.AstroViewModel
import com.samm.ktor01.presentation.GetAstroDataByDate
import com.samm.ktor01.presentation.GetAstroDataList
import com.samm.ktor01.presentation.GetSingleItemData

@Composable
fun AppNavigation(navController: NavHostController) {

    val viewModel: AstroViewModel = viewModel()

    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") {
            GetSingleItemData(viewModel = viewModel)
        }
        composable("screen2") {
            GetAstroDataByDate(viewModel = viewModel)
        }
        composable("screen3") {
            GetAstroDataList(viewModel = viewModel)
        }
    }
}