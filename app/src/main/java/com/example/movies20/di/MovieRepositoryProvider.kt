package com.example.movies20.di

import com.example.movies20.data.MovieRepository


internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}