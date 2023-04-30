package com.silverorange.videoplayer.data.remote

import retrofit2.http.GET

interface VideosApi {

    @GET("/videos")
    suspend fun getVideos(): String

}