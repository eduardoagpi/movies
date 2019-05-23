package com.cesaraguirre.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.cesaraguirre.movies.R
import com.cesaraguirre.movies.presentation.viewstate.MovieListItemViewState
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
        private val items: List<MovieListItemViewState>,
        private val clickListener: MoviesAdapterClickListener
): androidx.recyclerview.widget.RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.itemView.setOnClickListener { clickListener.onClickMovieItem(
                items[position].id
        ) }
        Glide.with(holder.itemView)
                .load(items[position].imageUrl)
                .into(holder.image)
    }

    class ViewHolder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val title = view.movie_item_title
        val image = view.movie_item_image
    }
}