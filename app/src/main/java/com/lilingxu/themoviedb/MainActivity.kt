package com.lilingxu.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lilingxu.themoviedb.ui.components.TheMovieTapRow
import com.lilingxu.themoviedb.ui.navigation.HomeScreen
import com.lilingxu.themoviedb.ui.navigation.Navigation
import com.lilingxu.themoviedb.ui.navigation.allScreens
import com.lilingxu.themoviedb.ui.navigation.navigateSingleTopTo
import com.lilingxu.themoviedb.ui.theme.TheMovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieDBTheme {
                val navController = rememberNavController()
                //bottomBar controller
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen = allScreens.find {
                    it.route == currentDestination?.route
                } ?: HomeScreen

                Scaffold(
                    modifier = Modifier,
                    bottomBar = {
                        TheMovieTapRow(
                            allScreens = allScreens,
                            onTabSelected = { screen ->
                                navController.navigateSingleTopTo(screen.route)
                            },
                            currentScreen = currentScreen
                        ) 
                    }
                ) {
                    Navigation(navController)

                }
            }
        }
    }
}

