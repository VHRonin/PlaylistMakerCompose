package com.example.playlistmakercompose.presentation.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.playlistmaker.Creator
import com.example.playlistmakercompose.presentation.components.navigation.AppNavHost
import com.example.playlistmakercompose.presentation.theme.PlaylistMakerComposeTheme
import com.example.playlistmakercompose.presentation.theme.ThemeVIewModelFactory
import com.example.playlistmakercompose.presentation.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<ThemeViewModel>(factory = ThemeVIewModelFactory(Creator.provideThemeInteractor(this)))
            PlaylistMakerComposeTheme(dynamicColor = false, darkTheme = viewModel.darkTheme) {
//                Scaffold(topBar = {
//                    MainTopBar()
//                }, containerColor = MaterialTheme.colorScheme.MainScreen, modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(modifier = Modifier.padding(innerPadding)) {
//                        MainScreen()
//                    }
//                }
                val navHostController = rememberNavController()

                AppNavHost(navHostController)
            }
        }
    }
}