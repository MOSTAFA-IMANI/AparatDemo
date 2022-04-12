package com.rymon.aparatdemo.data.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
        val id: String,
        val imgSrc: String,
        val link: String,
        val name: String,
        val patternBgColor: String,
        val patternBgSrc: String,
        val patternIconSrc: String,
        val videoCnt: String
    ): Parcelable