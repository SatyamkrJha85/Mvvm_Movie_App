package com.theapplication.apitest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.theapplication.apitest.Screens.BannerScreen
import com.theapplication.apitest.Screens.DetailsScreen
import com.theapplication.apitest.Screens.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.BannerScreen.route){
        composable(Route.BannerScreen.route){
            BannerScreen(navController = navController)
        }
        composable(Route.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(
            "Route.DetailsScreen.route/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id")
            DetailsScreen(id = id ?: -1,navController) // Provide a default value if id is null
        }

    }
}

sealed class Route(val route: String){
    object BannerScreen:Route("banner")
    object HomeScreen:Route("home")
    object DetailsScreen:Route("details")
}
