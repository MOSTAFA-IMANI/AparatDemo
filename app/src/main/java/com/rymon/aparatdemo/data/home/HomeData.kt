package com.rymon.aparatdemo.data.home

data class HomeData(
//    val attributes: Attributes,
    val id: String,
    val relationships: Relationships,
//    val type: String
) {
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
        val `data`: List<Data>
    )

    data class Data(
        val id: String,
        val type: String
    )
    data class Attributes(
//Data Attributes
        val ads: Boolean,
        val all: Boolean,
        val button: Any,
        val caption: Any,
        val line_count: Int,
        val link: Link,
        val more_type: String,
        val output_type: String,
        val theme: String,
        val title: Title,
        val total: Int
    )
}
