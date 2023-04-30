package com.silverorange.videoplayer.presentation.video_detail.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = "Video Player") })
}