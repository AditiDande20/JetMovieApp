package com.mobile.jetmovieapp.navigation

enum class MovieScreens {
    DetailScreen,
    HomeScreen;

    companion object{
        fun fromRoute(route : String?) : MovieScreens =
            when(route?.substringBefore("/")){

                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("$route not recognized")

            }
    }
}