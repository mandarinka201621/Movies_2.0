package com.example.movies20.features.movies

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies20.R
import com.example.movies20.di.MovieRepositoryProvider
import com.example.movies20.model.Movie
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment() {

    var listener: MovieItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_movies).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)

            val adapter = MovieListAdapter { it: Movie ->
                listener?.onMovieSelected(it)
            }

            this.adapter = adapter
            loadDataToAdapter(adapter)
        }
    }

    private fun loadDataToAdapter(adapter: MovieListAdapter) {
        val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
        lifecycleScope.launch {
            val moviesData = repository.loadMovies()

            adapter.submitList(moviesData)
        }
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    companion object {
        fun create() = MoviesListFragment()
    }

    interface MovieItemClickListener {
        fun onMovieSelected(movie: Movie)
    }
}