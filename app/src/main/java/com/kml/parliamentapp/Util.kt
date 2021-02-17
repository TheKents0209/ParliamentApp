package com.kml.parliamentapp

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.kml.parliamentapp.database.ParliamentMember
import java.lang.StringBuilder

fun formatMembers(members: List<ParliamentMember>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("<br>")
        members.forEach {
            append("<br>")
            append(it.firstname)
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}