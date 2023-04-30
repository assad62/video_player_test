package com.silverorange.videoplayer.domain.repository

interface VideosRepository {

    suspend fun getVideos():String

}