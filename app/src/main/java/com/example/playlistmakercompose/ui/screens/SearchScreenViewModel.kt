package com.example.playlistmakercompose.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playlistmakercompose.data.Track
import kotlinx.coroutines.launch

class SearchScreenViewModel : ViewModel() {
    var notFound by mutableStateOf(false)
        private set
    var noInternet by mutableStateOf(false)
        private set

    var tracks by mutableStateOf<List<Track>>(listOf())
    var query by mutableStateOf("")
    var lastFailedQuery by mutableStateOf("")


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
                if (query.isNotEmpty()){
                    val response = iTunesService.search(query)

                    if (response.isSuccessful){

                        val result = response.body()?.results ?: emptyList()

                        tracks = result

                        checkResponse(response.code())
                    }
                    else{
                        checkResponse(response.code())
                    }

                }

            }
            catch (e: Exception){
                checkResponse(-1)
            }
        }
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
}