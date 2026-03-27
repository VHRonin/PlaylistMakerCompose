package com.example.playlistmakercompose.ui.navigation

sealed class Destination(val route: String) {
    data object Main : Destination(MAIN)
    data object Settings : Destination(SETTINGS)
    data object Search : Destination(SEARCH)

    companion object{
        private const val MAIN = "main"
        private const val SETTINGS = "settings"
        private const val SEARCH = "search"
    }
}