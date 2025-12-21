package com.navigation.live.moviesapp.presentation.shared.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.navigation.live.moviesapp.presentation.movie_detail.view.MovieDetailScreen
import com.navigation.live.moviesapp.presentation.movies_list.view.AllMoviesScreen
import com.navigation.live.moviesapp.presentation.splash_screen.SplashScreen

sealed class Screen(
    val route: String
) {
    data object SplashScreen : Screen("splash_screen")
    data object AllMovies : Screen("all_movies_list")
    data object MovieDetail : Screen("movie_detail/{movieId}") {
        fun createRoute(movieId: String) = "movie_detail/$movieId"
    }
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route,
        modifier = modifier
    ) {

        composable(Screen.SplashScreen.route) {
            SplashScreen {
                navHostController.navigate(Screen.AllMovies.route){
                    popUpTo(Screen.SplashScreen.route){
                        inclusive = true
                    }
                }
            }
        }

        composable(Screen.AllMovies.route) {
            AllMoviesScreen(onMovieDetail = { movieId ->
                navHostController.navigate(Screen.MovieDetail.createRoute(movieId))
            })
        }

        composable(
            route = Screen.MovieDetail.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieDetailScreen(movieId) {
                navHostController.popBackStack()
            }
        }

    }


}