package com.silverorange.videoplayer.presentation.video_detail.components
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    url:String,
    viewModel: VideoPlayerViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = "", block = {
        viewModel.loadVideo(url)
        viewModel.player.playWhenReady = playWhenReady
        playerView.player = viewModel.player
    })

    AndroidView(factory = {
        playerView
    })
}