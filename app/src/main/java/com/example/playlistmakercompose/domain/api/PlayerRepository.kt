package com.example.playlistmakercompose.domain.api

import com.example.playlistmakercompose.domain.PlayerState

interface PlayerRepository {
    fun preparePlayer(previewUrl: String, onCompletion: () -> Unit)
    fun startPlayer(onStart: () -> Unit)
    fun pausePlayer(onPause: () -> Unit)
    fun releasePlayer()
    fun getCurrentTIme(): String
    fun getPlayerState(): PlayerState
}