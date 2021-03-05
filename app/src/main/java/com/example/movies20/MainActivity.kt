package com.example.movies20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies20.moviedetails.MoviesDetailsFragment
import com.example.movies20.movies.MoviesListFragment

class MainActivity : AppCompatActivity(), MoviesListFragment.MovieItemClickListener,
    MoviesDetailsFragment.MovieDetailsBackClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            routeToMovieList()
        }
    }

    override fun onMovieSelected() {
        routeDetailMovie()

    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMovieList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container,
                MoviesListFragment(),
                MoviesListFragment::class.java.simpleName
            )
            .commit()
    }

    private fun routeDetailMovie() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.main_container,
                MoviesDetailsFragment(),
                MoviesDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MoviesDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }
}