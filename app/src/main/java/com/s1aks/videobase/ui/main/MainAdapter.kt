package com.s1aks.videobase.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.s1aks.videobase.data.entities.Movie
import com.s1aks.videobase.databinding.FragmentMainItemBinding
import com.s1aks.videobase.ui.App.Companion.BASE_IMAGE_URL
import com.s1aks.videobase.utils.getColorFromIntProgress
import com.s1aks.videobase.utils.toFormattedDateText

class MainAdapter(
    private var itemClickListener: OnItemViewClickListener
) : ListAdapter<Movie, MainAdapter.MainViewHolder>(AdapterCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            FragmentMainItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MainViewHolder(private val binding: FragmentMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            currentList[position].let { movie ->
                poster.load(BASE_IMAGE_URL + movie.poster_path)
                title.text = movie.title
                date.text = movie.release_date?.toFormattedDateText
                val numberProgress = movie.vote_average?.toFloat()?.times(10.0)?.toInt() ?: 0
                progress.progress = numberProgress
                progressText.text = numberProgress.toString()
                progress.setIndicatorColor(numberProgress.getColorFromIntProgress)
                itemView.setOnClickListener { itemClickListener.onItemViewClick(movie.id) }
            }
        }
    }

    companion object AdapterCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }
}