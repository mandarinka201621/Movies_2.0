package com.example.movies20.movies

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movies20.R

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


    override fun onStart() {
        super.onStart()

        view?.findViewById<View>(R.id.movies_list_item_layout)?.setOnClickListener {
            listener?.onMovieSelected()
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
        fun onMovieSelected()
    }
}