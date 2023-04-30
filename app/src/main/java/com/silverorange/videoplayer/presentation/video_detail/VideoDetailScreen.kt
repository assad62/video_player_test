package com.silverorange.videoplayer.presentation.video_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VideoDetailScreen(){
    TopBar()
    TextCard()
}

@Preview
@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = "Video Player") })
}

@Preview
@Composable
fun TextCard() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello world!")
    }
}
