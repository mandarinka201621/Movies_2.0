package com.example.movies20.features.moviesdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies20.R
import com.example.movies20.model.Actor

class ActorsListAdapter() : ListAdapter<Actor, ActorsListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actorImage: ImageView = itemView.findViewById(R.id.image_actor)
        private val actorName: TextView = itemView.findViewById(R.id.name_actor)

        fun bind(actor: Actor) {
            actorImage.load(actor.imageUrl)
            actorName.text = actor.name
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem == newItem
        }
    }
}