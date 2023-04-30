package com.silverorange.videoplayer.data.repository

import com.silverorange.videoplayer.data.remote.VideosApi
import com.silverorange.videoplayer.domain.model.Video
import com.silverorange.videoplayer.domain.repository.VideosRepository
import javax.inject.Inject

class VideosRepositoryImpl @Inject constructor(
    private val api: VideosApi
) : VideosRepository {

    override suspend fun getVideos(): List<Video> {
       return  api.getVideos()
    }


}