package com.silverorange.videoplayer.presentation.video_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.silverorange.videoplayer.domain.model.Video

@Composable
fun VideoDetailScreen(
    viewModel: VideoDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()){
        TopBar()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(state.isLoading){

                CircularProgressIndicator()
            }
            TextCard(state.videos)
        }


    }

}

@Preview
@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = "Video Player") })
}

@Composable
fun TextCard(videos: List<Video>) {
    LazyColumn {
        items(videos) { video ->
            Text(text = video.description)
        }
    }

}
