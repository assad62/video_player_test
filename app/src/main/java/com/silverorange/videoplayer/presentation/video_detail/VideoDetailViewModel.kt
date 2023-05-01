package com.silverorange.videoplayer.presentation.video_detail

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
    val state: State<VideoListState> = _state

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

    private fun getVideos() {
        getVideosUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _state.value = VideoListState(videos = createLinkedList(result.data))
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
}