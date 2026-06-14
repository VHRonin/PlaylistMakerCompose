package com.example.playlistmakercompose.data

import com.example.playlistmakercompose.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}