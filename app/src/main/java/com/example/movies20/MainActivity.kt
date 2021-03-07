package com.example.movies20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies20.data.JsonMovieRepository
import com.example.movies20.data.MovieRepository
import com.example.movies20.di.MovieRepositoryProvider
import com.example.movies20.model.Movie
import com.example.movies20.features.moviesdetails.MoviesDetailsFragment
import com.example.movies20.features.movies.MoviesListFragment

class MainActivity : AppCompatActivity(), MoviesListFragment.MovieItemClickListener,
    MoviesDetailsFragment.MovieDetailsBackClickListener, MovieRepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            routeToMovieList()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        routeDetailMovie(movie)
    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMovieList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container,
                MoviesListFragment.create(),
                MoviesListFragment::class.java.simpleName
            )
            .commit()
    }

    private fun routeDetailMovie(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.main_container,
                MoviesDetailsFragment.create(movie.id),
                MoviesDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MoviesDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository
}