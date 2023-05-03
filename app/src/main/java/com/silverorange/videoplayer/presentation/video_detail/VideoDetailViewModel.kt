package com.silverorange.videoplayer.presentation.video_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverorange.videoplayer.common.Resource
import com.silverorange.videoplayer.domain.model.Video
import com.silverorange.videoplayer.domain.use_case.get_videos.GetVideosUseCase
import com.silverorange.videoplayer.utils.LinkedList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoDetailViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
):ViewModel(){

    private val _state = mutableStateOf(VideoListState())
    val state: State<VideoListState> get()= _state

    private val _videoIndex:MutableLiveData<Int> = MutableLiveData(0)
    val videoIndex:LiveData<Int> get() = _videoIndex

    init {
        getVideos()
    }

    private fun createLinkedList( videoList:List<Video>?):LinkedList<Video>{
        val videolinkedList:LinkedList<Video> = LinkedList()
        if (videoList != null) {
            for(video in videoList){
                videolinkedList.append(video)
            }
        }
        return videolinkedList
    }

    private fun createUrlList( videoList:List<Video>?):ArrayList<String>{
        val videoArrList:ArrayList<String> = ArrayList()
        if (videoList != null) {
            for(video in videoList){
                videoArrList.add(video.fullURL)
            }
        }
        return videoArrList
    }

    private fun getVideos() {
        getVideosUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = VideoListState(
                        videos = createLinkedList(result.data),
                        videoUrlList = createUrlList(result.data)
                    )
                }
                is Resource.Error -> {
                    _state.value = VideoListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = VideoListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }




    fun nextVideo(){
        val videosCount = _state.value.videos.count()
        var curr = _videoIndex.value

        if (curr != null) {
            if(curr < (videosCount - 1)){
                curr = curr.plus(1)
                _videoIndex.postValue(curr)
            }
        }

    }
    fun prevVideo(){
        var curr = _videoIndex.value
        if (curr != null) {
            //prevent navigate back on first video
            if(curr > 0){
                curr = curr.minus(1)
                _videoIndex.postValue(curr)
            }
        }

    }
}