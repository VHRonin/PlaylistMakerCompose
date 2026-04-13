package com.example.playlistmakercompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.components.AppBottomNavigation
import com.example.playlistmakercompose.ui.components.MyTopBar
@Composable
fun LibraryRoute(navController: NavController){
    Scaffold(
        topBar = {
            MyTopBar(
                headText = stringResource(R.string.media_library),
                onClick = {navController.popBackStack()}
            )
        },
        bottomBar = {
            AppBottomNavigation(navController)
        },
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LibraryScreen()
        }
    }
}

@Composable
fun LibraryScreen(){
    Text("Библиотека")
}