package com.samm.ktor01.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samm.ktor01.presentation.screens.DateSelectionScreen
import com.samm.ktor01.presentation.screens.FavoriteScreen
import com.samm.ktor01.presentation.screens.ListViewScreen
import com.samm.ktor01.presentation.screens.SingleItemViewScreen
import com.samm.ktor01.presentation.viewmodels.AstroViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: AstroViewModel) {


    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") {
            SingleItemViewScreen(state = viewModel.responseFlow)
        }
        composable("screen2") {
            DateSelectionScreen(
                state = viewModel.responseByDateFlowList,
                getData = viewModel::getDataByDate
            )
        }
        composable("screen3") {
            ListViewScreen(state = viewModel.responseFlowList, insert = viewModel::insertFavorite)
        }
        composable("screen4") {
            FavoriteScreen(viewModel = viewModel)
        }
    }
}