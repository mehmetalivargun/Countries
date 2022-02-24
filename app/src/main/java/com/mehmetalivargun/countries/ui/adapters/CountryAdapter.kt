package com.mehmetalivargun.countries.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.databinding.ItemCountryBinding
import dagger.hilt.android.AndroidEntryPoint

class CountryAdapter : ListAdapter<Country, CountryAdapter.ProductHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Country) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) =
        holder.bind(getItem(position))

    class ProductHolder(
        private val binding: ItemCountryBinding,
        private val itemClickListener: (Country) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Country) {
            binding.countryName.text = product.name
            binding.root.setOnClickListener {
                itemClickListener(product)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country) =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: Country, newItem: Country) =
                oldItem == newItem
        }
    }
}