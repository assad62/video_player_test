package com.silverorange.videoplayer.presentation.video_detail

import com.silverorange.videoplayer.domain.model.Video

data class VideoListState(
    val isLoading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val error: String = ""
)
