package com.example.movies20.model

import androidx.annotation.DrawableRes

data class Actor(
    val id: Int,
    val name: String,
    @DrawableRes val imageRes: Int
)
