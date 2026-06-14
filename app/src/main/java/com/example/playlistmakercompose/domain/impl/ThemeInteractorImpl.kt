package com.example.playlistmakercompose.domain.impl

import com.example.playlistmakercompose.domain.api.ThemeInteractor
import com.example.playlistmakercompose.domain.api.ThemeRepository

class ThemeInteractorImpl(private val themeRepository: ThemeRepository) : ThemeInteractor {
    override fun getCurrentTheme(): Boolean {
        return themeRepository.getCurrentTheme()
    }

    override fun saveTheme(darkThemeEnabled: Boolean) {
        themeRepository.saveTheme(darkThemeEnabled)
    }
}