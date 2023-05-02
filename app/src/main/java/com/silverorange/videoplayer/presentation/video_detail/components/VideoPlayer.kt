package com.silverorange.videoplayer.presentation.video_detail.components

import android.media.metrics.PlaybackStateEvent.STATE_ENDED
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.silverorange.videoplayer.R

@Composable
fun VideoPlayer(
    url:String,
    viewModel: VideoPlayerViewModel = hiltViewModel(),
    next:()->Unit,
    previous:()->Unit
){



    val context = LocalContext.current
    val playerView = StyledPlayerView(context)
    playerView.useController = false

    //playerView.setKeepContentOnPlayerReset(true);

    val playWhenReady by rememberSaveable {
        mutableStateOf(false)
    }
    viewModel.player.playWhenReady = playWhenReady
    playerView.player = viewModel.player
    viewModel.loadVideo(url)
    val shouldShowControls by remember { mutableStateOf(true) }

    var isPlaying by remember { mutableStateOf(viewModel.player.isPlaying) }

    val playbackState by remember { mutableStateOf(viewModel.player.playbackState) }


    Box(){
        AndroidView(factory = {
            playerView
        })
        PlayerControls(
            modifier = Modifier.align(Alignment.Center),
            isVisible = { shouldShowControls },
            isPlaying = { isPlaying },
            onReplayClick = {
                //pause on navigation
                isPlaying = false
                viewModel.player.pause()
                previous() },
            onForwardClick = {
                //pause on navigation
                isPlaying = false
                viewModel.player.pause()
                next() },
            onPauseToggle = { when {
                viewModel.player.isPlaying -> {
                    // pause the video
                    viewModel.player.pause()
                }
                viewModel.player.isPlaying.not() && playbackState == STATE_ENDED -> {
                    viewModel.player.seekTo(0)
                    viewModel.player.playWhenReady = true
                }
                else -> {
                    // play the video
                    // it's already paused
                    viewModel.player.play()
                }
            }
                isPlaying = isPlaying.not() },

            playbackState = { playbackState },
        )
    }

}


@Composable
 fun PlayerControls(
    modifier: Modifier = Modifier,
    isVisible: () -> Boolean,
    isPlaying: () -> Boolean,
    onReplayClick: () -> Unit,
    onForwardClick: () -> Unit,
    onPauseToggle: () -> Unit,
    playbackState: () -> Int,

) {

    val visible = remember(isVisible()) { isVisible() }

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(modifier = Modifier.background(Color.Black.copy(alpha = 0.6f))) {


            CenterControls(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                isPlaying = isPlaying,
                onReplayClick = onReplayClick,
                onForwardClick = onForwardClick,
                onPauseToggle = onPauseToggle,
                playbackState = playbackState
            )


        }
    }
}









@Composable
private fun CenterControls(
    modifier: Modifier = Modifier,
    isPlaying: () -> Boolean,
    playbackState: () -> Int,
    onReplayClick: () -> Unit,
    onPauseToggle: () -> Unit,
    onForwardClick: () -> Unit
) {
    val isVideoPlaying = remember(isPlaying()) { isPlaying() }

    val playerState = remember(playbackState()) { playbackState() }

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        IconButton(modifier = Modifier.size(40.dp), onClick = onReplayClick) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_previous),
                contentDescription = "Replay 5 seconds"
            )
        }

        IconButton(modifier = Modifier.size(40.dp), onClick = onPauseToggle) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter =
                when {
                    isVideoPlaying -> {
                        painterResource(id = R.drawable.ic_pause)
                    }

                    else -> {
                        painterResource(id = R.drawable.ic_play)
                    }
                },
                contentDescription = "Play/Pause"
            )
        }

        IconButton(modifier = Modifier.size(40.dp), onClick = onForwardClick) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Forward 10 seconds"
            )
        }
    }
}