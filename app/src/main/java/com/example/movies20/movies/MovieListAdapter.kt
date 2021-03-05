package com.example.movies20.movies

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies20.R

class MovieListAdapter(
    context: Context,
) {


    inner class  ViewHolder(view: View): RecyclerView.ViewHolder(view){
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


        fun bind(){}
    }
}