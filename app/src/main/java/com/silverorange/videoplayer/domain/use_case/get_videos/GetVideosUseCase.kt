package com.silverorange.videoplayer.domain.use_case.get_videos

import com.silverorange.videoplayer.common.Resource
import com.silverorange.videoplayer.domain.model.Video
import com.silverorange.videoplayer.domain.repository.VideosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val repository: VideosRepository
) {
    operator fun invoke(): Flow<Resource<List<Video>>> = flow {
        try {
            emit(Resource.Loading<List<Video>>())
            val videoList = repository.getVideos()
            emit(Resource.Success<List<Video>>(videoList))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Video>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Video>>("Couldn't reach server. Check your internet connection."))
        }
    }
}