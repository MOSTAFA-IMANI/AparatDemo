package com.rymon.aparatdemo.data.main


data class Data(
    val attributes: Attributes,
    val id: String,
    val relationships: Relationships,
    val type: String
)

data class Included(
    val attributes: AttributesX,
    val id: String,
    val relationships: RelationshipsX,
    val type: String
)

data class Links(
    val next: String
){
    fun getNextLinkPart():String =next.substringAfter("nextq=")
}

data class Attributes(
    val ads: Boolean,
    val all: Boolean,
    val button: Any,
    val caption: Any,
    val className: String,
    val id: String,
    val line_count: Int,
    val link: Link,
    val more_type: String,
    val output_type: String,
    val showAllLinkRouteNext: String,
    val theme: String,
    val title: Title,
    val total: Int
)

data class Relationships(
    val video: Video
)

data class Link(
    val showAll: Any,
    val text: String
)

data class Title(
    val caption: Any,
    val text: String
)

data class Video(
    val `data`: List<DataX>
)

data class DataX(
    val id: String,
    val type: String,
    var attributes: AttributesX?
)

data class AttributesX(
    val `360d`: String,
    val autoplay: Boolean,
    val avatar: String,
    val big_poster: String,
    val brand_priority: String,
    val caption: Any,
    val catId: String,
    val content_type: String,
    val date_exact: String,
    val description: String,
    val displayName: String,
    val duration: String,
    val file_link: Any,
    val file_link_all: Any,
    val follower_cnt: String,
    val frame: String,
    val hd: String,
    val id: String,
    val income_type: Any,
    val isAbroad: Boolean,
    val isCompany: Boolean,
    val isHidden: Boolean,
    val like: Like,
    val like_cnt: String,
    val link: String,
    val message_cnt: Int,
    val name: String,
    val official: String,
    val pic: String,
    val preview_src: String,
    val priority: String,
    val priority_type: String,
    val process: String,
    val profilePhoto: String,
    val sdate: String,
    val sdate_rss: String,
    val sdate_timediff: Int,
    val sender_name: String,
    val sensitive: Boolean,
    val share: Any,
    val small_poster: String,
    val tag_rel_id: String,
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

data class RelationshipsX(
    val channel: Channel
)

data class Like(
    val cnt: String
)


data class Watch(
    val avgWatchDuration: Int,
    val avgWatchDurationLabel: String,
    val durationPercentWatch: Int,
    val monthWatch: String,
    val text: String,
    val watchTimeMinStr: String
)

data class ExplainPersonalization(
    val categoryName: String,
    val categoryScore: Int,
    val channel: String,
    val onTheRise: Boolean,
    val picked: Boolean,
    val recommendationType: String,
    val rowType: String,
    val score: Double,
    val scoreAfter: Double,
    val type: String
)

data class Channel(
    val `data`: DataXX
)

data class DataXX(
    val id: String,
    val type: String
)

fun addVideoAttributesToData(data: Data, included: List<Included>) {

    data.relationships.video.data.forEach { currentData ->
        val currentIncluded = included.find {
            it.id == currentData.id
        }
        currentData.attributes= currentIncluded?.attributes
   val a = 10
    }
}