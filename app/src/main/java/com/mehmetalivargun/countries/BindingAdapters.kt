package com.mehmetalivargun.countries

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehmetalivargun.countries.data.api.CountryResponse
import com.mehmetalivargun.countries.ui.adapters.CountryAdapter

/*
val countryAdapter = CountryAdapter()
@BindingAdapter("app:countries")
fun RecyclerView.countries(products: List<CountryResponse>?) {
    if (adapter != countryAdapter) {
        adapter = countryAdapter
    }
    countryAdapter.submitList(products.orEmpty())
}*/
/*//////
@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this)
            .load(it)
            .into(this)
    }
}
*/

@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {


    /*
    imageUrl?.let {
        Glide.with(this)
            .load(it)
            .into(this)
    }*/

}

