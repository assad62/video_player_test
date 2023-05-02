package com.silverorange.videoplayer.presentation.video_detail.components

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.silverorange.videoplayer.domain.use_case.get_videos.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(

    val player: Player,
): ViewModel(){


    init {
        player.prepare()
    }

    fun loadVideo(url: String){
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}