package com.example.playlistmakercompose.domain.api

import com.example.playlistmakercompose.domain.models.Track

interface SearchHistoryRepository {
    fun getHistory(): ArrayList<Track>
    fun addTrackToHistory(track: Track, onHistoryClick: () -> Unit)
    fun clearHistory()
    fun fillTracksHistory()
    fun getTracks(): ArrayList<Track>
}