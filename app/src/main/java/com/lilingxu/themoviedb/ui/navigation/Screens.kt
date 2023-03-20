package com.lilingxu.themoviedb.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument


interface ScreenDestination {
    val icon: ImageVector
    val route: String
}

object WelcomeScreen : ScreenDestination {
    override val route = "welcome"
    override val icon = Icons.Filled.Home
}

object LoginScreen : ScreenDestination {
    override val route = "login"
    override val icon = Icons.Filled.Home
}
object AuthRequestScreen : ScreenDestination {
    override val route = "auth"
    override val icon = Icons.Filled.Home
}

object HomeScreen : ScreenDestination {
    override val route = "home"
    override val icon = Icons.Filled.Home
}

object DiscoverScreen : ScreenDestination {
    override val route = "discover"
    override val icon = Icons.Filled.Search
}

object FavoriteScreen : ScreenDestination {
    override val route = "favorite"
    override val icon = Icons.Filled.Favorite
}

object ProfileScreen : ScreenDestination {
    override val route = "profile"
    override val icon = Icons.Filled.Person
}

object SearchScreen : ScreenDestination {
    override val route = "search"
    override val icon = Icons.Filled.Search
}

object GenreScreen : ScreenDestination {
    override val route = "genre"
    override val icon = Icons.Filled.Search
    const val genreId = "genre_id"
    const val genreType = "genre_type"
    val routeWithArgs = "$route/{$genreId}/{$genreType}"
    val arguments = listOf(
        navArgument(genreId) {
            type = NavType.IntType
        },
        navArgument(genreType) {
            type = NavType.StringType
        }
    )
}

object MovieDetailsScreen : ScreenDestination {
    override val route = "movieDetails"
    override val icon = Icons.Filled.Warning
    const val movieId = "movie_id"
    val routeWithArgs = "$route/{$movieId}"
    val arguments = listOf(
        navArgument(movieId){
            type = NavType.IntType
        }
    )


}

// Screens to be displayed in the botBar
val allScreens = listOf(HomeScreen, DiscoverScreen, FavoriteScreen, ProfileScreen)
