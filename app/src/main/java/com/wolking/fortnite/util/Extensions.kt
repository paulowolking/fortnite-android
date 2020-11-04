package com.wolking.fortnite.util

import android.app.Activity
import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import com.wolking.fortnite.R
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> applyObservableAsync(): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun Activity.resourceByName(name: String, defType: String): Int {
    return this.resources.getIdentifier(name, defType, this.packageName)
}

fun Fragment.resourceByName(name: String, defType: String): Int {
    return this.activity?.resourceByName(name, defType) ?: 0
}


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