package com.silverorange.videoplayer.domain.repository

import com.silverorange.videoplayer.domain.model.Video

interface VideosRepository {
    suspend fun getVideos():List<Video>
}