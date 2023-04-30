package com.silverorange.videoplayer.data.remote

import com.silverorange.videoplayer.domain.model.Video
import retrofit2.http.GET

interface VideosApi {

    @GET("/videos")
    suspend fun getVideos():List<Video>

}