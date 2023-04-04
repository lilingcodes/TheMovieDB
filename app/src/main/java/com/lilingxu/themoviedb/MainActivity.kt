package com.lilingxu.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lilingxu.themoviedb.ui.components.TheMovieBottomBar
import com.lilingxu.themoviedb.ui.navigation.*
import com.lilingxu.themoviedb.ui.theme.TheMovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheMovieDBTheme {
                val navController = rememberNavController()
                //bottomBar
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination

                val context = LocalContext.current

                Scaffold(
                    bottomBar = {
                        if (shouldShowBottomBar(navController)) {
                            val currentScreen = allScreens.find {
                                it.route == currentDestination?.route
                            } ?: HomeScreen

                            TheMovieBottomBar(
                                allScreens = allScreens,
                                onTabSelected = { screen ->
                                    navController.navigateSingleTopTo(screen.route)
                                },
                                currentScreen = currentScreen
                            )
                        }
                    }
                ) {
                    Navigation(context, navController, Modifier.padding(it))
                }
            }
        }
    }
}

fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val currentDestination = navController.currentBackStackEntry?.destination
    return currentDestination?.route in listOf(
        HomeScreen.route,
        DiscoverScreen.route,
        FavoriteScreen.route,
        ProfileScreen.route
    )
}


