package com.rymon.aparatdemo.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import com.rymon.aparatdemo.databinding.HomeIncludedVideoAdapter2Binding


class HomeVideoAdapter2 : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<HomeVideoIncluded>()

    fun setMovies(movies: List<HomeVideoIncluded>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeIncludedVideoAdapter2Binding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val videoIncluded = movieList[position]
        holder.binding.name.text = videoIncluded.id
        Glide.with(holder.itemView.context).load(videoIncluded.attributes.big_poster).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: HomeIncludedVideoAdapter2Binding) : RecyclerView.ViewHolder(binding.root) {

}