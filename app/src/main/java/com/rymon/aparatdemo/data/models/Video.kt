package com.rymon.aparatdemo.data.models

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("360d")
    val is360: Boolean,
    @SerializedName("autoplay")
    val isAutoPlaying: Boolean,
    @SerializedName("big_poster")
    val bigPosterUrl: String,
    @SerializedName("create_date")
    val creationDate: String,
    @SerializedName("deleteurl")
    val deleteUrl: String,
    val duration: Int,
    @SerializedName("frame")
    val frameUrl: String,
    val id: String,
    val isHidden: Boolean,
    val official: String,
    val process: String,
    @SerializedName("profilePhoto")
    val profilePhotoUrl: String,
    @SerializedName("sdate")
    val persianSendingDate: String,
    @SerializedName("sdate_timediff")
    val sendingTimeDifference: Int,
    @SerializedName("sender_name")
    val senderName: String,
    @SerializedName("small_poster")
    val smallPosterUrl: String,
    val title: String,
    @SerializedName("uid")
    val uniqueId: String,
    @SerializedName("userid")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("video_date_status")
    val videoDateStatus: String,
    @SerializedName("visit_cnt")
    val visitCount: Int,

)