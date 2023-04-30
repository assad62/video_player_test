package com.silverorange.videoplayer.presentation.video_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverorange.videoplayer.common.Resource
import com.silverorange.videoplayer.domain.use_case.get_videos.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoDetailViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
):ViewModel(){

    private val _state = mutableStateOf(VideoDetailState())
    val state: State<VideoDetailState> = _state

    init {
        getVideos()
    }

    private fun getVideos() {
        getVideosUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = VideoDetailState(videos = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = VideoDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = VideoDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}