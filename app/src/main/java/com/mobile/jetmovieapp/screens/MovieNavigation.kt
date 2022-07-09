package com.mobile.jetmovieapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobile.jetmovieapp.navigation.MovieScreens
import com.mobile.jetmovieapp.screens.details.DetailScreen
import com.mobile.jetmovieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name){
        composable(MovieScreens.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie"){type = NavType.StringType})){
                backStack ->
            DetailScreen(navController,backStack.arguments?.getString("movie"))
        }
    }
}