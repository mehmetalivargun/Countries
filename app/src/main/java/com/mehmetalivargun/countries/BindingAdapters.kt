package com.mehmetalivargun.countries

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.ui.adapters.CountryAdapter

val countryAdapter = CountryAdapter()
@BindingAdapter("app:countries")
fun RecyclerView.countries(products: List<Country>?) {
    if (adapter != countryAdapter) {
        adapter = countryAdapter
    }
    countryAdapter.submitList(products.orEmpty())
}