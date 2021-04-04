package com.example.zarbondistributionclient.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.app.App

infix fun String.spannableText(query: String): SpannableString {
    val spanBuild = SpannableString(this)
    var startPos = -1
    var endPos = -1

    val fcs = ForegroundColorSpan(App.instance.resources.getColor(R.color.purple_700))
    startPos = this.indexOf(query, 0, ignoreCase = true)
    endPos = startPos + query.length
    if (startPos > -1) {
        spanBuild.setSpan(fcs, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    return spanBuild
}
