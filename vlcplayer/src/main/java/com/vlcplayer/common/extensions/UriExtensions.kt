package com.vlcplayer.common.extensions

import android.content.Context
import android.net.Uri

import android.webkit.URLUtil
import androidx.documentfile.provider.DocumentFile
import java.lang.IllegalArgumentException


/**
 * Get the display name of a Content Uri or the last path segment of
 */
fun Uri?.getName(context: Context): String {
    require(this != null)

    return when {
        URLUtil.isContentUrl(toString()) -> DocumentFile.fromSingleUri(context, this!!)?.name!!
        URLUtil.isFileUrl(toString()) -> this!!.lastPathSegment
        URLUtil.isNetworkUrl(toString()) -> this!!.lastPathSegment
        else -> throw IllegalArgumentException("Uri must have File or Content schema.")
    }
}