package com.example.playlistmakercompose.data.history

import com.example.playlistmakercompose.data.dto.TrackDto

interface SearchHistory {
    fun getHistory(): ArrayList<TrackDto>
    fun addTrackToHistory(track: TrackDto, onHistoryClick: () -> Unit)
    fun clearHistory()
    fun fillTracksHistory()
    fun getTracks(): ArrayList<TrackDto>
}