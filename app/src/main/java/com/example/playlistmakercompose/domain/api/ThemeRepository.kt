package com.example.playlistmakercompose.domain.api

interface ThemeRepository {
    fun getCurrentTheme(): Boolean
    fun saveTheme(darkThemeEnabled: Boolean)
}