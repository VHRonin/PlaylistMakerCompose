package com.example.playlistmakercompose.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.data.Track
import com.example.playlistmakercompose.ui.components.AppBottomNavigation
import com.example.playlistmakercompose.ui.components.MyTopBar
import com.example.playlistmakercompose.ui.screens.LibraryRoute
import com.example.playlistmakercompose.ui.screens.LibraryScreen
import com.example.playlistmakercompose.ui.screens.MainRoute
import com.example.playlistmakercompose.ui.screens.SearchRoute
import com.example.playlistmakercompose.ui.screens.SearchScreen
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

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination

    val showBars = currentRoute?.route in listOf(Destination.Search.route, Destination.Library.route, Destination.Settings.route)

    val currentDestination = listOf(Destination.Search, Destination.Library, Destination.Settings).find { it.route == currentRoute?.route }

    Scaffold(
        topBar = {
            if (showBars){
                MyTopBar(
                    headText = currentDestination?.label ?: "",
                    onClick = {}
                )
            }
        },
        bottomBar = {
            if (showBars){
                AppBottomNavigation(navController)
            }
        },
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Destination.Search.route,
//        enterTransition = { EnterTransition.None },
//        exitTransition = { ExitTransition.None },
//        popEnterTransition = { EnterTransition.None },
//        popExitTransition = { ExitTransition.None }
            ){
                composable(route = Destination.Main.route) {
                    MainRoute(onNavigate = onNavigate)
                }

                composable(route = Destination.Settings.route) {
                    SettingsRoute(onBackClick = onBackClick, navController)
                }

                composable(route = Destination.Search.route) {
                    SearchRoute(onBackClick = onBackClick, navController)
                }

                composable(route = Destination.Library.route) {
                    LibraryRoute(navController)
                }
            }
        }
    }


}

//fun NavHostController.navigateSafe(route: String){
//    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED){
//        navigate(route)
//    }
//}