package com.mehmetalivargun.countries

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load


@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {

    // api returns shorten wikipedia svg file link
    // could not get Original link from this shorten link
    // so flag cant load into the imageView
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()
    imageUrl?.let {
        val uri = it
        Log.e("uri", it)
        this.load(
            "http://commons.wikimedia.org/wiki/Special:FilePath/Flag%20of%20Albania.svg",
            imageLoader
        )
    }
}


