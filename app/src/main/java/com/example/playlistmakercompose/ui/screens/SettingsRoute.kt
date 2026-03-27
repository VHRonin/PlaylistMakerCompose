package com.example.playlistmakercompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.components.MyTopBar
import com.example.playlistmakercompose.ui.theme.MainScreen

@Composable
fun SettingsRoute(onBackClick: () -> Unit){
    Scaffold(topBar = {
        MyTopBar(
            headText = stringResource(R.string.settings),
            onClick = {onBackClick()}
        )
    },
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SettingsScreen()
        }
    }
}