package com.example.playlistmakercompose.presentation.theme

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playlistmakercompose.domain.api.ThemeInteractor

class ThemeVIewModelFactory(private val themeInteractor: ThemeInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ThemeViewModel(themeInteractor) as T
    }
}