package com.example.movies20.features.movies

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies20.R
import com.example.movies20.model.Movie

class MovieListAdapter(
    private val onClickCard: (item: Movie) -> Unit
) : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MoviesCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), onClickCard)
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieImage: ImageView = itemView.findViewById(R.id.movies_list_header_image)
        private val likeImage: ImageView = itemView.findViewById(R.id.movie_like_image)
        private val pgText: TextView = itemView.findViewById(R.id.pg_text)
        private val genreText: TextView = itemView.findViewById(R.id.film_genre_text)
        private val starsImages: List<ImageView> = listOf(
            itemView.findViewById(R.id.movie_rating_star1_image),
            itemView.findViewById(R.id.movie_rating_star2_image),
            itemView.findViewById(R.id.movie_rating_star3_image),
            itemView.findViewById(R.id.movie_rating_star4_image),
            itemView.findViewById(R.id.movie_rating_star5_image)
        )
        private val reviewsText: TextView = itemView.findViewById(R.id.movie_reviews_count_text)
        private val titleText: TextView = itemView.findViewById(R.id.film_name_text)
        private val movieLenText: TextView = itemView.findViewById(R.id.film_time_text)

        fun bind(movie: Movie, onClickCard: (item: Movie) -> Unit) {
            movieImage.setImageResource(movie.imageRes)

            val likeColor = if (movie.isLiked) {
                R.color.pink_light
            } else {
                R.color.white
            }
            ImageViewCompat.setImageTintList(
                likeImage,
                ColorStateList.valueOf(ContextCompat.getColor(likeImage.context, likeColor))
            )

            starsImages.forEachIndexed { index, imageView ->
                val colorId = if (index > movie.rating) R.color.pink_dark else R.color.gray_dark
                ColorStateList.valueOf(ContextCompat.getColor(imageView.context, colorId))
            }

            pgText.text = "${movie.pgAge}+"
            genreText.text = movie.genre
            reviewsText.text = "${movie.reviewCount} REVIEWS"
            titleText.text = movie.title
            movieLenText.text = movie.runningTime

            itemView.setOnClickListener {
                onClickCard(movie)
            }
        }


    }

    class MoviesCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
