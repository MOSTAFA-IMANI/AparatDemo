package com.rymon.aparatdemo.utils

import android.net.Uri
import android.os.Build
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import java.util.concurrent.TimeUnit


fun View.slideVisibility(visibility: Int, duration: Long = 400) {
    val transition: Transition = Fade()
    transition.duration = duration
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.visibility = visibility
}

fun String.findParameterInUrl(parameterKey: String): String? {
    if (parameterKey != "") {
        val uri = Uri.parse(this)
        val pathSegments: List<String> = uri.pathSegments
        val segmentIndex = pathSegments.indexOf(parameterKey)
        return if (segmentIndex == -1 || segmentIndex + 1 >= pathSegments.size) null
        else
            pathSegments[segmentIndex + 1]
    }
    return null
}


fun Long.convertSecondDuration(): String {
    var millis = this*1000
    if (millis >= 0) {
        val hours: Long = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(millis)


        return when {
            hours == 0L -> String.format("%02d:%02d", minutes, seconds)
            hours < 100 -> String.format("%02d:%02d:%02d", hours, minutes, seconds)
            else -> "~"
        }


    } else {
        return ""
    }
}