package com.example.playlistmakercompose.domain.api

import com.example.playlistmakercompose.domain.SearchResult

interface TracksRepository {
    fun searchTracks(term: String): SearchResult
}