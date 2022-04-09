package com.rymon.aparatdemo.ui.catogory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rymon.aparatdemo.data.category.Category
import com.rymon.aparatdemo.databinding.ItemMainCategoryBinding

import android.R
import android.widget.ImageView

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.DrawableImageViewTarget
import android.graphics.drawable.Drawable
import androidx.annotation.Nullable

import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.Transition


class CategoryAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var categoryList = mutableListOf<Category>()

    fun setCategories(categoryList: List<Category>) {
        this.categoryList = categoryList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainCategoryBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val category = categoryList[position]
        holder.binding.category = category
//        holder.binding.name.text = category.id


        Glide
            .with(holder.itemView.context)
            .load(category.patternIconSrc)
            .error(R.drawable.ic_dialog_alert)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.imageView)

        Glide
            .with(holder.itemView.context)
            .load(category.patternBgSrc)
            .optionalCenterCrop()
            .error(R.drawable.ic_dialog_alert)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?
                ) {
                    holder.binding.itemMainCategoryHolder.background =resource
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}


class MainViewHolder(val binding: ItemMainCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

}