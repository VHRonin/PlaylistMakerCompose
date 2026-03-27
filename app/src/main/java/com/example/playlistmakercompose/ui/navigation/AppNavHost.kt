package com.example.playlistmakercompose.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.playlistmakercompose.data.Track
import com.example.playlistmakercompose.ui.screens.MainRoute
import com.example.playlistmakercompose.ui.screens.SearchRoute
import com.example.playlistmakercompose.ui.screens.SettingsRoute
@Composable
fun AppNavHost(navController: NavHostController){
    val onNavigate = remember {
        { route: String ->
            navController.navigate(route){
                launchSingleTop = true
            }
        }
    }

    val onBackClick: () -> Unit = remember {
        { navController.popBackStack() }
    }

    NavHost(
        navController = navController,
        startDestination = Destination.Main.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ){
        composable(route = Destination.Main.route) {
            MainRoute(onNavigate = onNavigate)
        }

        composable(route = Destination.Settings.route) {
            SettingsRoute(onBackClick = onBackClick)
        }

        composable(route = Destination.Search.route) {
            SearchRoute(onBackClick = onBackClick)
        }
    }
}

//fun NavHostController.navigateSafe(route: String){
//    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED){
//        navigate(route)
//    }
//}