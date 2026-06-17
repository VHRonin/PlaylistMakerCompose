package com.example.playlistmakercompose.presentation.screens.player

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.playlistmakercompose.domain.PlayerState
import com.example.playlistmakercompose.domain.api.PlayerInteractor

class PlayerViewModel(val onPrepare: () -> Unit, val onStart: () -> Unit, val onPause: () -> Unit): ViewModel() {
    private lateinit var playerInteractor: PlayerInteractor

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var mediaRunnable: Runnable

    fun onPause() {
        pausePlayer()
    }

    fun onDestroy() {
        playerInteractor.releasePlayer()
    }

    fun handlePlayButton(){
        when (playerInteractor.getPlayerState()){
            is PlayerState.Playing -> pausePlayer()
            is PlayerState.Paused, is PlayerState.Prepared -> startPlayer()
            is PlayerState.Default -> {}
        }
    }

    fun preparePlayer(previewUrl: String){
        playerInteractor.preparePlayer(previewUrl, onPrepare)
    }

    fun startPlayer(){
        playerInteractor.startPlayer(onStart)

        mediaRunnable = createPlayerRunnable()
        handler.post(mediaRunnable)
    }

    fun pausePlayer(){
        playerInteractor.pausePlayer(onPause)
    }
    fun createPlayerRunnable(): Runnable {
        return object: Runnable {
            override fun run() {
                when (playerInteractor.getPlayerState()){
                    is PlayerState.Playing -> {
                        handler.postDelayed(this, TRACK_TIME_DELAY)
                    }
                    is PlayerState.Paused -> {
                        handler.removeCallbacks(this)
                    }
                    else -> {}
                }
            }
        }
    }

    fun getPlayerTime(): String = playerInteractor.getCurrentTIme()

    companion object{
        const val TRACK_TIME_DELAY = 300L
    }
}