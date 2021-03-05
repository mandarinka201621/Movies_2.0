package com.example.movies20.moviedetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movies20.R

class MoviesDetailsFragment : Fragment() {

    var listener: MovieDetailsBackClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onStart() {
        super.onStart()

        view?.findViewById<View>(R.id.back_button_layout)?.setOnClickListener {
            listener?.onMovieDeselected()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieDetailsBackClickListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        fun create() = MoviesDetailsFragment()
    }
}