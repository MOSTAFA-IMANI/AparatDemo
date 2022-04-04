package com.rymon.aparatdemo.api

import com.google.gson.annotations.SerializedName
import com.rymon.aparatdemo.data.main.Data
import com.rymon.aparatdemo.data.main.Included
import com.rymon.aparatdemo.data.main.Links
import com.rymon.aparatdemo.data.search.Ui
import com.rymon.aparatdemo.data.search.Ui2
import com.rymon.aparatdemo.data.search.Videobysearch
import com.rymon.aparatdemo.data.search.Videobysearch2


data class SearchResponse2(
    val `data`: List<Data>,
    val included: List<Included>,
    val links: Links?
)