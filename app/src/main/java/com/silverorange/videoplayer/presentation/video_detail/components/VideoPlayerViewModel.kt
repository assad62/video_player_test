package com.silverorange.videoplayer.presentation.video_detail.components

import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(val player: ExoPlayer): ViewModel(){


    init {
        player.prepare()
    }

    fun initVideoPlayer(urlList: List<String>){
        val videoMediaItemList = mutableListOf<MediaItem>()
        for(url in urlList){
            val mediaItem = MediaItem.fromUri(url)
            videoMediaItemList.add(mediaItem)
        }
        player.setMediaItems(videoMediaItemList)
    }


    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}