package com.silverorange.videoplayer.domain.use_case.get_videos

import com.silverorange.videoplayer.common.Resource
import com.silverorange.videoplayer.domain.model.Video
import com.silverorange.videoplayer.domain.repository.VideosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val repository: VideosRepository
) {

    private fun sortByDate(videoList:List<Video>):List<Video>{
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        return videoList.sortedByDescending { format.parse(it.publishedAt) }
    }

    operator fun invoke(): Flow<Resource<List<Video>>> = flow {
        try {
            emit(Resource.Loading())
            val videoList = sortByDate(repository.getVideos())
            emit(Resource.Success(videoList))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}