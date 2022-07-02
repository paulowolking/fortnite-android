package com.wolking.fortnite.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wolking.fortnite.R

fun ImageView.load(url: String?, circleCrop: Boolean = false, errorImage: Int? = null) {
    val requestOptions = RequestOptions()

    errorImage?.let { requestOptions.error(it) }

    if (circleCrop) requestOptions.circleCrop()

    Glide.with(this.context)
        .load(url ?: errorImage ?: R.drawable.ic_launcher_background)
        .apply(requestOptions)
        .into(this)
}

fun runDelayed(delay: Long = 3000, block: () -> Unit) {
    android.os.Handler().postDelayed({
        block()
    }, delay)
}