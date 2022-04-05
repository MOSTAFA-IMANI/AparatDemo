package com.rymon.aparatdemo.api

import com.rymon.aparatdemo.data.search.Ui
import com.rymon.aparatdemo.data.search.Videobysearch


data class SearchResponse(
    val ui: Ui,
    val videobysearch: List<Videobysearch>?
)