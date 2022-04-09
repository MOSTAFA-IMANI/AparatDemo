package com.rymon.aparatdemo.api

import com.google.gson.annotations.SerializedName
import com.rymon.aparatdemo.data.main.Data
import com.rymon.aparatdemo.data.main.Included
import com.rymon.aparatdemo.data.main.Links


data class MainHomeResponse(
    @SerializedName("data")
    val mainData: List<Data>,
    val included: List<Included>,
    val links: Links?
)