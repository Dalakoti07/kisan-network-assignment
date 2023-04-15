package com.dalakoti.network.kisan.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import com.dalakoti.network.kisan.R

fun ImageView.loadImageByUrl(
    url: String?,
    @DrawableRes errorDrawableRes: Int = R.drawable.ic_error
) {

    // To set a placeholder, use placeholder() as lambda param below. Currently we don't want to show it.
    load(url) {
        crossfade(true)
        placeholder(
            R.drawable.placeholder
        )
        error(errorDrawableRes)
    }
}
