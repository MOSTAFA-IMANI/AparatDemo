package com.rymon.aparatdemo.ui.home

import  android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import com.rymon.aparatdemo.databinding.ItemSearchedVideoBinding

class HomeVideoAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<HomeVideoIncluded, HomeVideoAdapter.VideoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding =
            ItemSearchedVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class VideoViewHolder(private val binding: ItemSearchedVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(video: HomeVideoIncluded) {
            binding.apply {
               /*
                Glide.with(itemView)
                    .load("video.attributes.big_poster")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
*/
                textViewUserName.text = "video.attributes.sender_name"
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(video: HomeVideoIncluded)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<HomeVideoIncluded>() {
            override fun areItemsTheSame(oldItem: HomeVideoIncluded, newItem: HomeVideoIncluded) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HomeVideoIncluded, newItem: HomeVideoIncluded) =
                oldItem == newItem
        }
    }
}