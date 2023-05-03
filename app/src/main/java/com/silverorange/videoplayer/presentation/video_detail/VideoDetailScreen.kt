package com.silverorange.videoplayer.presentation.video_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.silverorange.videoplayer.presentation.video_detail.components.TopBar
import com.silverorange.videoplayer.presentation.video_detail.components.VideoDescription
import com.silverorange.videoplayer.presentation.video_detail.components.VideoPlayer

@Composable
fun VideoDetailScreen(
    viewModel: VideoDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val urlList = viewModel.state.value.videoUrlList


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

                    VideoPlayer(urlList = urlList
                        , next = {

                            viewModel.nextVideo()

                        }, previous = {

                            viewModel.prevVideo()
                        })
                     //moved to separate composable to prevent
                    //needless recomposition of video player when index was being changed
                     videoDetails()




            }


        }
    }


}

@Composable
fun videoDetails( viewModel: VideoDetailViewModel = hiltViewModel()){

    val index = viewModel.videoIndex.observeAsState().value

    val video = index?.let { viewModel.state.value.videos.nodeAtIndex(it)?.value }
    val title = video?.title
    val authorName = video?.author?.name


    Spacer(modifier = Modifier.size(10.dp))
    Column(){

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment =   Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                "Title: ",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W800,
                    fontStyle = FontStyle.Normal,

                    )
            )
            if (title != null) {
                Text(text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W800,
                        fontStyle = FontStyle.Normal,
                    ))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment =   Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text("Author: ",
                style = TextStyle(
                    fontSize = 21.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W800,
                    fontStyle = FontStyle.Normal,
                )
            )
            if (authorName != null) {
                Text(text = authorName,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W800,
                        fontStyle = FontStyle.Normal,
                    )
                )
            }
        }
    }
    Spacer(modifier = Modifier.size(10.dp))

    video?.description?.let {
            it1 ->
        VideoDescription(videoDescription = it1)
    }

}