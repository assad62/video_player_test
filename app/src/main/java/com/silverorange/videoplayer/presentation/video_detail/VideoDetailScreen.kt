package com.silverorange.videoplayer.presentation.video_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.silverorange.videoplayer.domain.model.Video
import com.silverorange.videoplayer.presentation.video_detail.components.TextCard
import com.silverorange.videoplayer.presentation.video_detail.components.TopBar
import com.silverorange.videoplayer.presentation.video_detail.components.VideoPlayer
import com.silverorange.videoplayer.utils.LinkedList
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun VideoDetailScreen(

    viewModel: VideoDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val index = viewModel.videoIndex

    Scaffold(topBar = { TopBar() }) {
        Box(modifier = Modifier.fillMaxSize()){

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(state.isLoading){

                    CircularProgressIndicator()
                }
                index.value?.let {
                        it1 -> state.videos.nodeAtIndex(it1)?.value?.let { it1 -> VideoPlayer(url = it1.fullURL ) } }

            }


        }
    }


}




