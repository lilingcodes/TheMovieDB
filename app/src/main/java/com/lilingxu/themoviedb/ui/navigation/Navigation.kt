package com.lilingxu.themoviedb.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lilingxu.themoviedb.repository.LoginRepository
import com.lilingxu.themoviedb.ui.screens.*
import com.lilingxu.themoviedb.ui.viewmodel.LoginViewModel
import com.lilingxu.themoviedb.ui.viewmodel.RegisterViewModel

@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues) {
    val loginRepository = LoginRepository()
    val loginViewModel = LoginViewModel(loginRepository)
    val registerViewModel = RegisterViewModel()
    //val getPopularMoviesUseCase: GetPopularMoviesUseCase =
    //val egisterViewModel = HomeViewModel(getPopularMoviesUseCase)


    //val context = LocalContext.current

    //val loginViewModel = ViewModelProvider( ViewModelFactory(loginRepository)).get(LoginViewModel::class.java)


    NavHost(navController = navController, startDestination = DiscoverScreen.route) {
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
                loginOnClick = { navController.navigate(HomeScreen.route) },
                modifier = Modifier.padding(16.dp),
                viewModel = loginViewModel

            )
        }
        composable(RegisterScreen.route) {
            RegisterScreen(
                registerOnClick = {
                    navController.navigate(HomeScreen.route)
                },
                modifier = Modifier.padding(16.dp),
                viewModel = registerViewModel
            )
        }
        composable(HomeScreen.route) {
            HomeScreen(Modifier.padding(paddingValues))
        }

        composable(DiscoverScreen.route) {
            DiscoverScreen(
                searchFieldOnClick = {},
                genreTypeOnClick = {
                    navController.navigateToGenre(it)
                }
            )

        }

        composable(
            route = GenreScreen.routeWithArgs,
            arguments = GenreScreen.arguments
        ) {
            val genreType = it.arguments?.getInt(GenreScreen.genreType)
            requireNotNull(genreType)
            GenreScreen(genreType, Modifier.padding(paddingValues))
        }

        composable(FavoriteScreen.route) {
            FavoriteScreen()
        }
        composable(ProfileScreen.route) {
            ProfileScreen()
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

fun NavHostController.navigateToGenre(typeId: Int) =
    this.navigate("${GenreScreen.route}/${typeId}"){
        popUpTo(
            this@navigateToGenre.graph.findStartDestination().id
        ) {
            saveState = true
        }

    }