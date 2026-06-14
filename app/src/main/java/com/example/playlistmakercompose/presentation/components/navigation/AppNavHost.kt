package com.example.playlistmakercompose.presentation.components.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.playlistmakercompose.presentation.components.AppBottomNavigation
import com.example.playlistmakercompose.presentation.components.MyTopBar
import com.example.playlistmakercompose.presentation.screens.library.LibraryRoute
import com.example.playlistmakercompose.presentation.screens.main.MainRoute
import com.example.playlistmakercompose.presentation.screens.player.PlayerScreen
import com.example.playlistmakercompose.presentation.screens.search.SearchRoute
import com.example.playlistmakercompose.presentation.screens.settings.SettingsRoute
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
            MyTopBar(
                headText = currentDestination?.label ?: "",
                onClick = {
                    if (showBars) {

                    } else {
                        navController.popBackStack()
                    }
                },
                showBackButton = if (showBars) false else true
            )
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

                composable(route = Destination.Player.route) {
                    PlayerScreen(navController)
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