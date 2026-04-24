package com.example.playlistmakercompose.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playlistmakercompose.ui.screens.SearchScreenViewModel

class SearchViewModelFactory(private  val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchScreenViewModel(sharedPreferences) as T
    }
}