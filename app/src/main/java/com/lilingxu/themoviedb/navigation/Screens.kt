package com.lilingxu.themoviedb.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


interface ScreenDestination {
    val icon: ImageVector
    val route: String
}

/**
 * TheMovieDB app navigation destinations
 */
object WelcomeScreen : ScreenDestination {
    override val icon = Icons.Filled.Info
    override val route = "welcome"
}

object LoginScreen : ScreenDestination {
    override val icon = Icons.Filled.Info
    override val route = "login"
}

object RegisterScreen : ScreenDestination {
    override val icon = Icons.Filled.Info
    override val route = "register"
}

object HomeScreen : ScreenDestination {
    override val icon = Icons.Filled.Home
    override val route = "home"
}

object SearchScreen : ScreenDestination {
    override val icon = Icons.Filled.Search
    override val route = "search"
}

object FavoriteScreen : ScreenDestination {
    override val icon = Icons.Filled.Favorite
    override val route = "favorite"
}

object ProfileScreen : ScreenDestination {
    override val icon = Icons.Filled.Person
    override val route = "profile"
}

/*
object Home : ScreenDestination {
    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
    override val icon = Icons.Filled.Money
    override val route = "home"
    const val accountTypeArg = "account_type"
    val routeWithArgs = "$route/{$accountTypeArg}"
    val arguments = listOf(
        navArgument(accountTypeArg){
            type = NavType.StringType
        }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "rally://$route/{$accountTypeArg}"}
    )

    *//*val deepLinks = listOf(
        navDeepLink {
            navDeepLink { uriPattern = "rally://$route/{$accountTypeArg}"}
        }
    )*/


// Screens to be displayed in the botBar
val allScreens = listOf(HomeScreen, SearchScreen, FavoriteScreen, ProfileScreen)
