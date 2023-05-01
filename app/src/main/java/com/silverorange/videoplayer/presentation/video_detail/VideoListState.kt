package com.silverorange.videoplayer.presentation.video_detail

import com.silverorange.videoplayer.domain.model.Video
import com.silverorange.videoplayer.utils.LinkedList

data class VideoListState(
    val isLoading: Boolean = false,
    val videos: LinkedList<Video> = LinkedList(),
    val error: String = ""
)
