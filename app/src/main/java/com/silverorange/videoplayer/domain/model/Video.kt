package com.silverorange.videoplayer.domain.model

data class Video (
    val id: String,
    val title: String,
    val hlsURL: String,
    val fullURL: String,
    val description: String,
    val publishedAt: String,
    val author: Author
)
