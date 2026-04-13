package com.example.playlistmakercompose.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.playlistmakercompose.R

sealed class Destination(val route: String, val iconId: Int, val label: String) {
    data object Main : Destination(MAIN, R.drawable.ic_search, "Поиск")
    data object Settings : Destination(SETTINGS, R.drawable.ic_settings, "Настройки")
    data object Search : Destination(SEARCH, R.drawable.ic_search, "Поиск")
    data object Library : Destination(LIBRARY, R.drawable.ic_library, "Медиатека")

    companion object{
        private const val MAIN = "main"
        private const val SETTINGS = "settings"
        private const val SEARCH = "search"
        private const val LIBRARY = "library"
    }
}