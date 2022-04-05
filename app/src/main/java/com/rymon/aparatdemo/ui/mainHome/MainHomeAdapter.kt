package com.rymon.aparatdemo.ui.mainHome

import  android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rymon.aparatdemo.data.main.Data
import com.rymon.aparatdemo.databinding.ItemMainHomeBinding

class MainHomeAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Data, MainHomeAdapter.VideoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding =
            ItemMainHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class VideoViewHolder(private val binding: ItemMainHomeBinding) :
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

        fun bind(data: Data) {
            binding.apply {

               val horizontalVideoData =  data.relationships.video.data
                val adapter = MainHomeVideoHorizontalAdapter(horizontalVideoData)

                recyclerViewHorizontal.adapter = adapter
                textViewCategoryTitle.text = data.attributes.title.text


               /*

               recyclerViewHorizontal
                Glide.with(itemView)
                    .load(data.relationships.video.data[0].attributes?.big_poster)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
                textViewUserName.text = data.relationships.video.data[0].attributes?.big_poster

                */
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(video: Data)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Data, newItem: Data) =
                oldItem == newItem
        }
    }
}