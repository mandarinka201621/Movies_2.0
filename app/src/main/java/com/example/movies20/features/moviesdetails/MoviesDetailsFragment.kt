package com.example.movies20.features.features.moviesdetails

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies20.R
import com.example.movies20.model.Movie

class MoviesDetailsFragment : Fragment() {

    var listener: MovieDetailsBackClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieData = arguments?.getSerializable(PARAM_MOVIE_DATA) as? Movie ?: return

        updateMovieDetailsInfo(movieData)
        view.findViewById<RecyclerView>(R.id.recycler_actors).apply {
            val adapter = ActorsListAdapter()
            this.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            this.adapter = adapter
            adapter.submitList(movieData.actors)
        }

        view.findViewById<View>(R.id.back_button_layout)?.setOnClickListener {
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

    private fun updateMovieDetailsInfo(movie: Movie) {
        view?.findViewById<ImageView>(R.id.movie_logo_image)
            ?.setImageResource(movie.detailImageRes)

        view?.findViewById<TextView>(R.id.movie_age_restrictions_text)?.text =
            context?.getString(R.string.movies_list_allowed_age_template, movie.pgAge)

        view?.findViewById<TextView>(R.id.movie_name_text)?.text = movie.title
        view?.findViewById<TextView>(R.id.movie_tags_text)?.text = movie.genre
        view?.findViewById<TextView>(R.id.movie_reviews_count_text)?.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        view?.findViewById<TextView>(R.id.movie_storyline_text)?.text = movie.storyLine

        val starsImages = listOf<ImageView?>(
            view?.findViewById(R.id.movie_rating_star1_image),
            view?.findViewById(R.id.movie_rating_star2_image),
            view?.findViewById(R.id.movie_rating_star3_image),
            view?.findViewById(R.id.movie_rating_star4_image),
            view?.findViewById(R.id.movie_rating_star5_image)
        )
        starsImages.forEachIndexed { index, imageView ->
            imageView?.let {
                val colorId =
                    if (movie.rating > index) R.color.pink_light else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }
    }

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        private const val PARAM_MOVIE_DATA = "movie_data"

        fun create(movie: Movie) = MoviesDetailsFragment().also {
            val args = bundleOf(
                PARAM_MOVIE_DATA to movie
            )
            it.arguments = args
        }
    }
}