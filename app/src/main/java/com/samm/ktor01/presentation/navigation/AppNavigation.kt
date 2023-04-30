package com.samm.ktor01.presentation.navigation

import androidx.compose.runtime.Composable
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
    listOfFavorites: List<Apod>,
    navController: NavHostController,
    viewModel: AstroViewModel,
) {

    val apod = Apod(
        copyright = "copyright",
        date = "date",
        explanation= "explanation",
        hdUrl = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg",
        mediaType = "image",
        serviceVersion = "v1",
        title = "Title",
        url = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg"
    )

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
            DateSelectionScreen(
                state = viewModel.responseByDateFlowList,
                getData = viewModel::getDataByDate,
                insert = viewModel::insertFavorite,
                unFavorite = viewModel::deleteFavorite
            )
        }
        composable("screen3") {
            ListViewScreen(
                state = viewModel.responseFlowList,
                insert = viewModel::insertFavorite
            )
        }
        composable("screen4") {
            FavoriteScreen(
                favoritesList = listOfFavorites,
                unFavorite = viewModel::deleteFavorite,
                insert = viewModel::insertFavorite
            )
        }
    }
}