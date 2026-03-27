package com.example.playlistmakercompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.playlistmakercompose.ui.theme.MainScreen

@Composable
fun MainRoute(onNavigate: (String) -> Unit){
    Scaffold(topBar = {
        MainTopBar()
    }, containerColor = MaterialTheme.colorScheme.MainScreen, modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainScreen(onNavigate = onNavigate)
        }
    }
}