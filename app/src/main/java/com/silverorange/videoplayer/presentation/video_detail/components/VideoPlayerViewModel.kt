package com.silverorange.videoplayer.presentation.video_detail.components

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.silverorange.videoplayer.VideoPlayer

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(val player: ExoPlayer): ViewModel(){

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