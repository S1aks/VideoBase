package com.s1aks.videobase.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.s1aks.videobase.data.entities.Cast
import com.s1aks.videobase.databinding.FragmentDetailsActorsItemBinding
import com.s1aks.videobase.ui.App

class CastAdapter : ListAdapter<Cast, CastAdapter.CastViewHolder>(AdapterCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(
            FragmentDetailsActorsItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CastViewHolder(private val binding: FragmentDetailsActorsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            currentList[position].let { cast ->
                photo.load(App.BASE_IMAGE_URL + cast.profile_path)
                actorName.text = cast.name
                roleName.text = cast.character
            }
        }
    }

    companion object AdapterCallback : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Cast, newItem: Cast) = oldItem == newItem
    }
}