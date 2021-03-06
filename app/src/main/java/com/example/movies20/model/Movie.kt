package com.example.movies20.model

import androidx.annotation.DrawableRes

data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genre: String,
    val runningTime: String,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    @DrawableRes val imageRes: Int,
    @DrawableRes val detailImageRes: Int,
    val storyLine: String,
    val actors: List<Actor>
)