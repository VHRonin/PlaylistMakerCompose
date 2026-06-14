package com.example.playlistmakercompose.domain.api

import com.example.playlistmakercompose.domain.SearchResult

interface TracksInteractor {
    fun searchTracks(term: String, consumer: TracksConsumer)

    interface TracksConsumer{
        fun consume(searchResult: SearchResult)
    }
}