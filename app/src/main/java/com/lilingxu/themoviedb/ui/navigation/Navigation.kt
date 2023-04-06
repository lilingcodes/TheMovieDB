package com.lilingxu.themoviedb.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val sheetMovie: Movie by mainViewModel.sheetMovie.observeAsState(Movie())
    val startDestination: String by mainViewModel.startDestination.observeAsState(WelcomeScreen.route)
    LaunchedEffect(true) {
        mainViewModel.updateStartDestination()
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(WelcomeScreen.route) {
            WelcomeScreen(
                context = context,
                loginOnClick = { navController.navigate(LoginScreen.route) },
                theMovieDBRegisterOnClick = {
                    mainViewModel.registerWithTMDB(it)
                    navController.navigate(AuthRequestScreen.route)

                },
                modifier = Modifier.padding(16.dp)
            )
        }

        composable(LoginScreen.route) {
            LoginScreen(
                forgotPasswordOnClick = { /*TODO*/ },
                loginOnClick = {
                    navController.navigateSingleTopTo(HomeScreen.route)
                    mainViewModel.updateStartDestination()
                },
                modifier = Modifier.padding(16.dp),
            )
        }

        composable(AuthRequestScreen.route) {
            AuthRequestScreen(
                onSuccess = { navController.navigateSingleTopTo(LoginScreen.route) },
                onFail = { /*navController.navigateSingleTopTo(WelcomeScreen.route)*/ },
                modifier = Modifier.padding(16.dp),
            )
        }


        composable(HomeScreen.route) {
            HomeScreen(
                sheetContent = { sheetState ->
                    BottomSheetContent(sheetMovie) {
                        if (sheetState.isVisible)
                            navController.homeNavigateToMovieDetails(sheetMovie.id)
                    }
                },
                setSheetContent = { movie ->
                    if (sheetMovie != movie) {
                        mainViewModel.setSheetMovie(movie)
                    }
                },
                modifier = Modifier
            )
        }

        composable(DiscoverScreen.route) {
            DiscoverScreen(
                modifier = Modifier,
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
            val genreId = it.arguments?.getInt(GenreScreen.genreId)!!
            val genreName = it.arguments?.getString(GenreScreen.genreType)!!
            GenreScreen(
                genreId,
                genreName,
                arrowBackOnClick = { navController.popBackStack() },
                sheetContent = {
                    BottomSheetContent(sheetMovie) {
                        navController.genreNavigateToMovieDetails(sheetMovie.id)
                    }
                },
                setSheetContent = { movie ->
                    if (sheetMovie != movie) {
                        mainViewModel.setSheetMovie(movie)
                    }
                },
                modifier = Modifier
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

        composable(
            route = MovieDetailsScreen.routeWithArgs,
            arguments = MovieDetailsScreen.arguments
        ) {
            val movieId = it.arguments?.getInt(MovieDetailsScreen.movieId)!!

            MovieDetailsScreen(
                movieId = movieId,
                arrowBackOnClick = { navController.popBackStack() }
            )
        }
    }

}

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

fun NavHostController.homeNavigateToMovieDetails(movieId: Int) =
    this.navigate("${MovieDetailsScreen.route}/${movieId}") {
        popUpTo(
            HomeScreen.route
        ) {
            saveState = true
        }

    }

fun NavHostController.genreNavigateToMovieDetails(movieId: Int) =
    this.navigate("${MovieDetailsScreen.route}/${movieId}") {
        popUpTo(
            GenreScreen.route
        ) {
            saveState = true
        }

    }

