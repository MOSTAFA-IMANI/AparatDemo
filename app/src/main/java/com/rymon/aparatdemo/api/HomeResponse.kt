package com.rymon.aparatdemo.api

import com.google.gson.annotations.SerializedName
import com.rymon.aparatdemo.data.home.HomeData
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import com.rymon.aparatdemo.data.home.links

data class HomeResponse(
    @SerializedName("links")
    val links: links,
//    val data: List<HomeData>,
    val included: List<HomeVideoIncluded>
)