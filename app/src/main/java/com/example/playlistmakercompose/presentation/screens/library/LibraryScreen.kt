package com.example.playlistmakercompose.presentation.screens.library

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LibraryRoute(navController: NavController){
    LibraryScreen()
//    Scaffold(
//        topBar = {
//            MyTopBar(
//                headText = stringResource(R.string.media_library),
//                onClick = {navController.popBackStack()}
//            )
//        },
//        bottomBar = {
//            AppBottomNavigation(navController)
//        },
//        modifier = Modifier.fillMaxSize()) { innerPadding ->
//        Column(modifier = Modifier.padding(innerPadding)) {
//            LibraryScreen()
//        }
//    }
}

@Composable
fun LibraryScreen(){
    Text("Библиотека")
}