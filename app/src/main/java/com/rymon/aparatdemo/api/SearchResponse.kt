package com.rymon.aparatdemo.api

import com.google.gson.annotations.SerializedName
import com.rymon.aparatdemo.data.search.AparatPagingHelper
import com.rymon.aparatdemo.data.models.Video


data class SearchResponse(
    @SerializedName("ui")
    val pagingHelperParameters: AparatPagingHelper,
    @SerializedName("videobysearch")
    val searchedVideo: List<Video>?
)