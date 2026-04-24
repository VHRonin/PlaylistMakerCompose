package com.example.playlistmakercompose.data

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class SearchHistory(private val sharedPreferences: SharedPreferences) {
    val tracks = mutableStateListOf<Track>()
//    val tracks = ArrayList<Track>()
    private val gson = Gson()
    //var a = mutableStateListOf<List<Track>>(listOf())

    fun getHistory(): ArrayList<Track>{
        val history = sharedPreferences.getString(HISTORY_KEY, null) ?: return arrayListOf()

        val type = object : TypeToken<ArrayList<Track>>() {}.type

        return gson.fromJson(history, type)
    }

    fun addTrackToHistory(track: Track, onHistoryClick: () -> Unit = {}){
        fillTracksHistory()

        tracks.removeAll {it.trackId == track.trackId}

        tracks.add(0, track)

        if (tracks.size > MAX_SIZE) tracks.removeAt(tracks.size - 1)

        val json = gson.toJson(tracks)
        sharedPreferences.edit {
            putString(HISTORY_KEY, json)
        }

        onHistoryClick()
    }

    fun clear(){
        sharedPreferences.edit {
            remove(HISTORY_KEY)
        }

        tracks.clear()
    }

    fun fillTracksHistory(){
        tracks.clear()
        tracks.addAll(getHistory())
    }

    companion object {
        const val HISTORY_KEY = "history_key"
        const val MAX_SIZE = 10
    }

}