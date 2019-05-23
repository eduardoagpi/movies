package com.cesaraguirre.movies.presentation.adapter

import androidx.recyclerview.widget.ListAdapter
import com.cesaraguirre.movies.presentation.viewstate.SearchResultViewState
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_result.view.*
import androidx.recyclerview.widget.DiffUtil
import com.cesaraguirre.movies.R


class SearchResultsAdapter(
        private val clickListener: MoviesAdapterClickListener
): ListAdapter<SearchResultViewState, SearchResultsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_search_result, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.searchResult.text = getItem(position).resultTitle
        holder.itemView.setOnClickListener { clickListener.onClickMovieItem(
                getItem(position).movieId
        ) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchResult: TextView = itemView.search_result_lbl
    }
}

val DIFF_CALLBACK: DiffUtil.ItemCallback<SearchResultViewState> = object : DiffUtil.ItemCallback<SearchResultViewState>() {
    override fun areItemsTheSame(oldItem: SearchResultViewState, newItem: SearchResultViewState): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    override fun areContentsTheSame(oldItem: SearchResultViewState, newItem: SearchResultViewState): Boolean {
        return oldItem.resultTitle == newItem.resultTitle
    }

}