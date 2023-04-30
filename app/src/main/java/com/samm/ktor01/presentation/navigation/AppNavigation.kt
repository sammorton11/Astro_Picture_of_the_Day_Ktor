package com.samm.ktor01.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.presentation.screens.DateSelectionScreen
import com.samm.ktor01.presentation.screens.FavoriteScreen
import com.samm.ktor01.presentation.screens.ListViewScreen
import com.samm.ktor01.presentation.screens.SingleItemViewScreen
import com.samm.ktor01.presentation.viewmodels.AstroViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: AstroViewModel,
) {

    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            val state = viewModel.responseFlow.collectAsStateWithLifecycle()

            SingleItemViewScreen(
                state = state.value,
                insert = viewModel::insertFavorite,
                unFavorite = viewModel::deleteFavorite
            )
        }
        composable("screen2") {
            val state = viewModel.responseByDateFlowList.collectAsStateWithLifecycle()

            DateSelectionScreen(
                state = state.value,
                getData = viewModel::getDataByDate,
                insert = viewModel::insertFavorite,
                unFavorite = viewModel::deleteFavorite
            )
        }
        composable("screen3") {
            val state = viewModel.responseFlowList.collectAsStateWithLifecycle()

            ListViewScreen(
                state = state.value,
                insert = viewModel::insertFavorite,
                unFavorite = viewModel::deleteFavorite
            )
        }
        composable("screen4") {
           val state = viewModel.favorites.collectAsState()

            FavoriteScreen(
                favoritesList = state.value,
                unFavorite = viewModel::deleteFavorite,
                insert = viewModel::insertFavorite
            )
        }
    }
}