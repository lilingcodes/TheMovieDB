package com.lilingxu.themoviedb.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.components.BottomSheetContent
import com.lilingxu.themoviedb.ui.view.*
import com.lilingxu.themoviedb.ui.viewmodel.MainViewModel

@Composable
fun Navigation(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val sheetMovie: Movie by mainViewModel.sheetMovie.observeAsState(Movie())
    val startDestination: String by mainViewModel.startDestination.observeAsState(WelcomeScreen.route)
    mainViewModel.updateStartDestination()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(WelcomeScreen.route) {
            WelcomeScreen(
                context = context,
                loginOnClick = { navController.navigate(LoginScreen.route) },
                theMovieDBRegisterOnClick = {
                    mainViewModel.registerWithTMDB(it)
                    navController.navigate(AuthRequestScreen.route)

                },
                modifier = modifier.padding(16.dp)
            )
        }

        composable(LoginScreen.route) {
            LoginScreen(
                forgotPasswordOnClick = { /*TODO*/ },
                loginOnClick = {
                    navController.navigateSingleTopTo(HomeScreen.route)
                    mainViewModel.updateStartDestination()
                },
                modifier = modifier.padding(16.dp),
            )
        }

        composable(AuthRequestScreen.route) {
            AuthRequestScreen(
                onSuccess = { navController.navigateSingleTopTo(LoginScreen.route) },
                onFail = { navController.navigateSingleTopTo(WelcomeScreen.route) },
                modifier = modifier.padding(16.dp),
            )
        }


        composable(HomeScreen.route) {
            HomeScreen(
                sheetContent = { BottomSheetContent(sheetMovie, modifier) },
                setSheetContent = { movie ->
                    mainViewModel.setSheetMovie(movie)
                },
                modifier = modifier
            )
        }

        composable(DiscoverScreen.route) {
            DiscoverScreen(
                modifier = modifier,
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
                sheetContent = { BottomSheetContent(sheetMovie, modifier) },
                setSheetContent = { movie ->
                    mainViewModel.setSheetMovie(movie)
                },
                modifier
            )
        }

        composable(FavoriteScreen.route) {
            FavoriteScreen(modifier)
        }

        composable(ProfileScreen.route) {
            ProfileScreen(modifier) {
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
