package com.rymon.aparatdemo.ui.catogory.subCategory

import  android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.data.models.Video
import com.rymon.aparatdemo.databinding.FragmentSearchBinding
import com.rymon.aparatdemo.databinding.ItemSearchedVideoBinding
import com.rymon.aparatdemo.databinding.ItemSubCategoryBinding

class SubCategoryAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Video, SubCategoryAdapter.VideoViewHolder>(VIDEO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding =
            ItemSubCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoryAdapter.VideoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class VideoViewHolder(private val binding: ItemSubCategoryBinding) :
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

        fun bind(currentItem: Video) {
            binding.apply {
                binding.video = currentItem

                Glide.with(itemView)
                    .load(video?.smallPosterUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }

        }
    }
    interface OnItemClickListener {
        fun onItemClick(video: Video)

    }

    companion object {
        private val VIDEO_COMPARATOR = object : DiffUtil.ItemCallback<Video>() {
            override fun areItemsTheSame(oldItem: Video, newItem: Video) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Video, newItem: Video) =
                oldItem == newItem
        }
    }
}