package com.rymon.aparatdemo.utils

import android.net.Uri
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup

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
        return if (segmentIndex == -1 ||segmentIndex+1 >= pathSegments.size) null
        else
            pathSegments[segmentIndex + 1]
    }
    return null
}