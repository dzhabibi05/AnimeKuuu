package com.pemmob.animefind.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pemmob.animefind.data.repository.AnimeRepository
import com.pemmob.animefind.ui.detail.DetailScreen
import com.pemmob.animefind.ui.home.HomeViewModel
import com.pemmob.animefind.ui.navigation.ViewModelFactory.provideDetailViewModelFactory
import com.pemmob.animefind.ui.navigation.ViewModelFactory.provideHomeViewModelFactory
import com.pemmob.animefind.ui.home.HomeScreen
import com.pemmob.animefind.ui.detail.DetailViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    repository: AnimeRepository
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        // Destination: Home Screen
        composable(Routes.HOME) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = provideHomeViewModelFactory(repository)
            )
            HomeScreen(
                viewModel = homeViewModel,
                onAnimeClick = { malId ->
                    navController.navigate(Routes.createDetailRoute(malId))
                }
            )
        }

        // Destination: Detail Screen
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("malId") { type = NavType.IntType }
            )
        ) {
            val detailViewModel: DetailViewModel = viewModel(
                factory = provideDetailViewModelFactory(repository)
            )
            DetailScreen(
                viewModel = detailViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}