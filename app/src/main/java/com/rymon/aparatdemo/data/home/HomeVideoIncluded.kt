package com.rymon.aparatdemo.data.home

data class HomeVideoIncluded(
    val attributes: IncludedAttributes,
    val id: String,
    val type: String
){
    data class IncludedAttributes(
        val `360d`: String,
        val autoplay: Boolean,
        val big_poster: String,
        val brand_priority: String,
        val catId: String,
        val content_type: String,
        val date_exact: String,
        val description: String,
        val duration: String,
        val file_link: Any,
        val file_link_all: Any,
        val frame: String,
        val hd: String,
        val id: String,
        val isAbroad: Boolean,
        val isCompany: Boolean,
        val isHidden: Boolean,
        val like: Like,
        val like_cnt: String,
        val meta: Meta,
        val official: String,
        val preview_src: String,
        val process: String,
        val profilePhoto: String,
        val sdate: String,
        val sdate_rss: String,
        val sdate_timediff: Int,
        val sender_name: String,
        val sensitive: Boolean,
        val share: Any,
        val small_poster: String,
        val tags: List<String>,
        val title: String,
        val uid: String,
        val userid: String,
        val username: String,
        val videovisit: Any,
        val visit_cnt: String,
        val visit_cnt_int: String,
        val watch: Watch
    )
    data class Like(
        val cnt: String
    )
    data class Meta(
        val _type: String
    )
    data class Watch(
        val avgWatchDuration: Int,
        val avgWatchDurationLabel: String,
        val durationPercentWatch: Int,
        val monthWatch: String,
        val text: String,
        val watchTimeMinStr: String
    )
}
