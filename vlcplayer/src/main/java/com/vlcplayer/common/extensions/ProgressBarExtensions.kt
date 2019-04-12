package com.vlcplayer.common.extensions


import android.widget.ProgressBar
import androidx.core.content.ContextCompat


/**
 * Set the color of the [ProgressBar] using the provided [colorId].
 */
internal fun ProgressBar.setColor(colorId: Int) = indeterminateDrawable.setColorFilter(
        ContextCompat.getColor(context, colorId)
        , android.graphics.PorterDuff.Mode.SRC_IN
)