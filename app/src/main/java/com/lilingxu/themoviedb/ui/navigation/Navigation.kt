package com.lilingxu.themoviedb.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lilingxu.themoviedb.ui.screens.*
import com.lilingxu.themoviedb.ui.viewmodel.MainViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val startDestination = mainViewModel.startDestination.collectAsState().value
    mainViewModel.updateStartDestination()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(WelcomeScreen.route) {
            WelcomeScreen(
                loginOnClick = { navController.navigate(LoginScreen.route) },
                registerOnClick = { navController.navigate(RegisterScreen.route) },
                modifier = Modifier.padding(16.dp)
            )
        }

        composable(LoginScreen.route) {
            LoginScreen(
                forgotPasswordOnClick = { navController.navigate(RegisterScreen.route) },
                loginOnClick = {
                    navController.navigateSingleTopTo(HomeScreen.route)
                    mainViewModel.updateStartDestination()
                },
                createNewUser = { navController.navigate(DiscoverScreen.route) },
                modifier = Modifier.padding(16.dp),
            )
        }

        composable(RegisterScreen.route) {
            RegisterScreen(
                registerOnClick = {
                    navController.navigateSingleTopTo(HomeScreen.route)
                },
                modifier = Modifier.padding(16.dp),
            )
        }

        composable(HomeScreen.route) {
            HomeScreen(Modifier.padding(paddingValues))
        }

        composable(DiscoverScreen.route) {
            DiscoverScreen(
                searchFieldOnClick = {},
                genreTypeOnClick = { id, name ->
                    navController.navigateToGenre(id, name)
                }
            )

        }

        composable(
            route = GenreScreen.routeWithArgs,
            arguments = GenreScreen.arguments
        ) {
            val genreType = it.arguments?.getInt(GenreScreen.genreId)!!
            val genreName = it.arguments?.getString(GenreScreen.genreType)!!
            GenreScreen(
                genreType,
                genreName,
                arrowBackOnClick = { navController.popBackStack() },
                Modifier.padding(paddingValues)
            )
        }

        composable(FavoriteScreen.route) {
            FavoriteScreen()
        }

        composable(ProfileScreen.route) {
            ProfileScreen() {
                navController.navigateSingleTopTo(WelcomeScreen.route)
                mainViewModel.updateStartDestination()

            }
        }
    }

}


/*fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/${accountType}")
}*/

fun NavHostController.navigateSingleTopTo(
    route: String,
) =
    this.navigate(route) {
        //esto significa que, cuando se presiona la flecha hacia atrás desde cualquier destino, se lleva toda la pila de actividades a HOME.
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        //esto significa que, si presionas la misma pestaña varias veces, no se inician varias copias del mismo destino.
        launchSingleTop = true
        //esto significa que, cuando se vuelve a presionar la misma pestaña, se conservan los datos y el estado del usuario anterior en la pantalla sin volver a cargarlos.
        restoreState = true
    }

fun NavHostController.navigateToGenre(genreId: Int, genreName: String) =
    this.navigate("${GenreScreen.route}/${genreId}/${genreName}") {
        popUpTo(
            DiscoverScreen.route
        ) {
            saveState = true
        }

    }


