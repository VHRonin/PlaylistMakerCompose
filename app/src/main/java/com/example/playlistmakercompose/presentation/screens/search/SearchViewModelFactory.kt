package com.example.playlistmakercompose.presentation.screens.search

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playlistmakercompose.domain.api.SearchHistoryInteractor
import com.example.playlistmakercompose.domain.api.TracksInteractor

class SearchViewModelFactory(private val searchHistory: SearchHistoryInteractor, private val tracksInteractor: TracksInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchScreenViewModel(searchHistory, tracksInteractor) as T
    }
}