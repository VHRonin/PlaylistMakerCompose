package com.example.playlistmaker

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.playlistmakercompose.data.MediaPlayerClient
import com.example.playlistmakercompose.data.PlayerRepositoryImpl
import com.example.playlistmakercompose.data.SearchHistoryRepositoryImpl
import com.example.playlistmakercompose.data.ThemeRepositoryImpl
import com.example.playlistmakercompose.data.TracksRepositoryImpl
import com.example.playlistmakercompose.data.history.SearchHistory
import com.example.playlistmakercompose.data.history.SearchHistoryImpl
import com.example.playlistmakercompose.data.network.RetrofitNetworkClient
import com.example.playlistmakercompose.data.player.MediaPlayerClientImpl
import com.example.playlistmakercompose.domain.api.PlayerInteractor
import com.example.playlistmakercompose.domain.api.PlayerRepository
import com.example.playlistmakercompose.domain.api.SearchHistoryInteractor
import com.example.playlistmakercompose.domain.api.SearchHistoryRepository
import com.example.playlistmakercompose.domain.api.ThemeInteractor
import com.example.playlistmakercompose.domain.api.ThemeRepository
import com.example.playlistmakercompose.domain.api.TracksRepository
import com.example.playlistmakercompose.domain.impl.PlayerInteractorImpl
import com.example.playlistmakercompose.domain.impl.SearchHistoryInteractorImpl
import com.example.playlistmakercompose.domain.impl.ThemeInteractorImpl
import com.example.playlistmakercompose.domain.impl.TracksInteractorImpl

object Creator {
    private const val SEARCH_PREFERENCES = "search_preferences"
    private const val APP_PREFERENCES = "app_preferences"
    private fun getRetrofitNetworkClient(): RetrofitNetworkClient {
        return RetrofitNetworkClient()
    }
    private fun getTracksRepositoryImpl(): TracksRepositoryImpl{
        return TracksRepositoryImpl(getRetrofitNetworkClient())
    }
    private fun getMediaPlayerClient(): MediaPlayerClient{
        return MediaPlayerClientImpl()
    }
    private fun getPlayerRepository(): PlayerRepository{
        return PlayerRepositoryImpl(getMediaPlayerClient())
    }
    private fun gerSharedPreferences(context: Context) = context.getSharedPreferences(SEARCH_PREFERENCES, MODE_PRIVATE)
    private fun getSearchHistoryImpl(context: Context): SearchHistory{
        return SearchHistoryImpl(gerSharedPreferences(context))
    }
    private fun getSearchHistoryRepository(context: Context): SearchHistoryRepository{
        return SearchHistoryRepositoryImpl(getSearchHistoryImpl(context))
    }
    private fun getThemeRepository(context: Context): ThemeRepository{
        return ThemeRepositoryImpl( context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE))
    }
    fun provideTrackInteractor(): TracksInteractorImpl{
        return TracksInteractorImpl(getTracksRepositoryImpl())
    }
    fun providePlayerInteractor(): PlayerInteractor{
        return PlayerInteractorImpl(getPlayerRepository())
    }
    fun provideSearchHistoryInteractor(context: Context): SearchHistoryInteractor{
        return SearchHistoryInteractorImpl(getSearchHistoryRepository(context))
    }
    fun provideThemeInteractor(context: Context): ThemeInteractor{
        return ThemeInteractorImpl(getThemeRepository(context))
    }
}