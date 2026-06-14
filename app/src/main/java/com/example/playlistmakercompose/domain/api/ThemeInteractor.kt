package com.example.playlistmakercompose.domain.api

interface ThemeInteractor {
    fun getCurrentTheme(): Boolean
    fun saveTheme(darkThemeEnabled: Boolean)
}