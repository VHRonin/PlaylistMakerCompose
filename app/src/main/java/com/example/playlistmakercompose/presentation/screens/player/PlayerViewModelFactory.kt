package com.example.playlistmakercompose.presentation.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playlistmakercompose.presentation.screens.search.SearchScreenViewModel

class PlayerViewModelFactory(val onPrepare: () -> Unit, val onStart: () -> Unit, val onPause: () -> Unit): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayerViewModel(onPrepare, onStart, onPause) as T
    }
}