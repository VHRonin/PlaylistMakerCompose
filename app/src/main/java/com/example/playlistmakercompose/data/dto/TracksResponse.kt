package com.example.playlistmakercompose.data.dto

class TracksResponse(
    val resultCount: Int,
    val results: List<TrackDto>
) : Response()