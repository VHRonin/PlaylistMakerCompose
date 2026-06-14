package com.example.playlistmakercompose.domain.impl

import com.example.playlistmakercompose.domain.api.TracksInteractor
import com.example.playlistmakercompose.domain.api.TracksRepository
import java.util.concurrent.Executors

class TracksInteractorImpl(private val repository: TracksRepository) : TracksInteractor {
    private val executors = Executors.newCachedThreadPool()
    override fun searchTracks(
        term: String,
        consumer: TracksInteractor.TracksConsumer
    ) {
        executors.execute {
            consumer.consume(repository.searchTracks(term))
        }
    }
}