package com.silverorange.videoplayer.presentation.video_detail.components

import androidx.compose.runtime.Composable
import com.silverorange.videoplayer.domain.model.Video
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun TextCard(video: Video) {

    MarkdownText(markdown = video.title)

}