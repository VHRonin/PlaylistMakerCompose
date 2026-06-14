package com.example.playlistmakercompose.presentation.theme

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.example.playlistmakercompose.domain.api.ThemeInteractor

class ThemeViewModel(private val themeInteractor: ThemeInteractor) : ViewModel() {
    var darkTheme by mutableStateOf(
        themeInteractor.getCurrentTheme()
    )

    fun switchTheme(darkThemeEnabled: Boolean){
        darkTheme = darkThemeEnabled
        themeInteractor.saveTheme(darkThemeEnabled)
    }
    companion object{
        const val APP_PREFERENCES = "app_preferences"
        const val DARK_THEME_KEY = "dark_theme"
    }
}