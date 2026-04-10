package com.example.playlistmakercompose.network

import com.example.playlistmakercompose.data.Track

class TracksResponse(
    val resultCount: Int,
    val results: List<Track>
)