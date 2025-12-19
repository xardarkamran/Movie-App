package com.navigation.live.moviesapp.presentation.shared.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.navigation.live.moviesapp.presentation.movie_detail.view.MovieDetailScreen
import com.navigation.live.moviesapp.presentation.movies_list.view.AllMoviesScreen

sealed class Screen(
    val route: String
) {
    object AllMovies : Screen("all_movies_list")
    object MovieDetail : Screen("movie_detail")
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AllMovies.route,
        modifier = modifier
    ) {

        composable(Screen.AllMovies.route) {
            AllMoviesScreen(onMovieDetail = {
                navHostController.navigate(Screen.MovieDetail.route)
            })
        }

        composable(Screen.MovieDetail.route) {
            MovieDetailScreen()
        }

    }


}