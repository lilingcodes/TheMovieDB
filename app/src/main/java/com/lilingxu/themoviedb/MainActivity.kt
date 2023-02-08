package com.lilingxu.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lilingxu.themoviedb.navigation.HomeScreen
import com.lilingxu.themoviedb.navigation.Navigation
import com.lilingxu.themoviedb.navigation.allScreens
import com.lilingxu.themoviedb.ui.components.TheMovieTapRow
import com.lilingxu.themoviedb.ui.theme.TheMovieDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieDBTheme {
                val navController = rememberNavController()
                //Gestionar bottomBar
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
                                navController.navigate(screen.route)
                            },
                            currentScreen = currentScreen
                        )
                    }
                ) {
                    Navigation(this, navController)
                }
            }
        }
    }
}

