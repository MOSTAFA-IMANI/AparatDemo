package com.rymon.aparatdemo.ui.mainHome

import  android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rymon.aparatdemo.R
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.rymon.aparatdemo.data.main.DataX

class MainHomeVideoHorizontalAdapter(private val mList: List<DataX>) :
    RecyclerView.Adapter<MainHomeVideoHorizontalAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video_main_home, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val videoData = mList[position]

        // sets the image to the imageview from our itemHolder class
        Glide.with(holder.itemView)
            .load(videoData.attributes?.big_poster)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(holder.imageView)

        // sets the text to the textview from our itemHolder class

        holder.textView.text = videoData.attributes?.title ?: "null"

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        var textView: TextView = itemView.findViewById(R.id.text_view_user_name)
    }


}
