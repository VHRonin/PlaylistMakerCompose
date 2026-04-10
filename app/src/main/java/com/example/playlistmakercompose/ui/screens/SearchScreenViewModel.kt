package com.example.playlistmakercompose.ui.screens

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


    fun updateText(text: String){
        query = text
    }
    fun clearText(){
        query = ""
    }
    fun searchTracks(){
        viewModelScope.launch {
            try {
                if (query.isNotEmpty()){
                    val response = iTunesService.search(query)

                    if (response.isSuccessful){
//                        tracks.clear()

                        val result = response.body()?.results ?: emptyList()

                        tracks = result

//                        if (response.body()?.results?.isNotEmpty() == true){
//                            tracks.addAll(response.body()?.results!!)
//                        }
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
                    notFound = false
                    noInternet = false
                }
            }

            else -> {
                tracks = emptyList()
                noInternet = true
            }
        }
    }
}