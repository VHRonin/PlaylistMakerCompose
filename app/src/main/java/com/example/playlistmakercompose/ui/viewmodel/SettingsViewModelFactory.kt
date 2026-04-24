package com.example.playlistmakercompose.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playlistmakercompose.ui.screens.SettingsScreenViewModel

class SettingsViewModelFactory(private  val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsScreenViewModel(sharedPreferences) as T
    }
}