package com.example.playlistmakercompose.presentation.screens.search

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playlistmaker.Creator
import com.example.playlistmakercompose.domain.SearchResult
import com.example.playlistmakercompose.domain.api.SearchHistoryInteractor
import com.example.playlistmakercompose.domain.api.TracksInteractor
import com.example.playlistmakercompose.domain.models.Track
import kotlinx.coroutines.launch

class SearchScreenViewModel(val searchHistory: SearchHistoryInteractor, private val tracksInteractor: TracksInteractor) : ViewModel() {
    var notFound by mutableStateOf(false)
        private set
    var noInternet by mutableStateOf(false)
        private set

    var tracks by mutableStateOf<List<Track>>(listOf())
    var historyTracks by mutableStateOf<List<Track>>(listOf())
        private set
    var query by mutableStateOf("")
    var lastFailedQuery by mutableStateOf("")
    var isSearchFieldFocused by mutableStateOf(false)

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())
    private val searchRunnable = Runnable{searchTracks(query)}


    fun updateText(text: String){
        query = text
    }
    fun clearText(){
        query = ""
    }

    fun clearTracks(){
        tracks = emptyList()
    }

    fun clearMessageVisibility(){
        notFound = false
        noInternet = false
    }
    fun searchTracks(query: String){
        viewModelScope.launch {
            try {
//                if (query.isNotEmpty()){
//                    val response = iTunesService.search(query)
//
//                    if (response.isSuccessful){
//
//                        val result = response.body()?.results ?: emptyList()
//
//                        tracks = result
//
//                        checkResponse(response.code())
//                    }
//                    else{
//                        checkResponse(response.code())
//                    }
//
//                }

                tracksInteractor.searchTracks(query, object : TracksInteractor.TracksConsumer{
                    override fun consume(searchResult: SearchResult) {
                        when (searchResult){
                            is SearchResult.Success -> {
                                tracks = searchResult.foundTracks
                                checkResponse(searchResult.code)
                            }
                            is SearchResult.NothingFound -> {
                                checkResponse(searchResult.code)
                            }
                            else -> {
                                checkResponse((searchResult as SearchResult.NetworkError).code)
                            }
                        }
                    }

                })

            }
            catch (e: Exception){
                checkResponse(-1)
            }
        }
    }

    fun clearHistory(){
        searchHistory.clearHistory()
        clearTracks()
        historyTracks = emptyList()
    }

    fun loadHistory(){
        historyTracks = searchHistory.getTracks()
    }

    private fun checkResponse(code: Int){
        when (code) {
            200 -> {
                if (tracks.isEmpty()) {
                    tracks = emptyList()
                    notFound = true
                } else {
                    clearMessageVisibility()
                }
            }

            else -> {
                tracks = emptyList()
                noInternet = true
                lastFailedQuery = query
            }
        }
    }

    fun debounceClick(): Boolean{
        val current = isClickAllowed
        if (isClickAllowed){
            isClickAllowed = false
            handler.postDelayed({isClickAllowed = true}, CLICK_DEBOUNCE_DELAY)
        }

        return current
    }

    companion object{
        const val CLICK_DEBOUNCE_DELAY = 1000L
        const val SEARCH_DEBOUNCE_DELAY = 1000L
    }
}