package com.mehmetalivargun.countries

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load


@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {

    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()

    imageUrl?.let {
        val uri = it
        this.load(imageUrl, imageLoader)
    }
}


