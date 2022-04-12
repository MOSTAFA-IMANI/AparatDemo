package com.rymon.aparatdemo.api

import com.google.gson.annotations.SerializedName
import com.rymon.aparatdemo.data.search.AparatPagingHelper
import com.rymon.aparatdemo.data.models.Video


data class SubCategoryResponse(
    @SerializedName("ui")
    val pagingHelperParameters: AparatPagingHelper,
    @SerializedName("categoryvideos")
    val categoryVideo: List<Video>?
)