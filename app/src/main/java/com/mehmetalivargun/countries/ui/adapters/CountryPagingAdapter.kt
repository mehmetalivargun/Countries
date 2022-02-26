package com.mehmetalivargun.countries.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehmetalivargun.countries.R
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.data.api.CountryResponse
import com.mehmetalivargun.countries.databinding.ItemCountryBinding

class CountryPagingAdapter() :
    PagingDataAdapter<Country, CountryPagingAdapter.ViewHolder>(DiffUtilCallBack) {

    var onItemClickListener: ((Country) -> Unit)? = null
    var onFavClickListener: ((Country) -> Unit)? = null
    val scaleAnimation = ScaleAnimation(
        0.7f,
        1.0f,
        0.7f,
        1.0f,
        Animation.RELATIVE_TO_SELF,
        0.7f,
        Animation.RELATIVE_TO_SELF,
        0.7f
    )
    val bounceInterpolator = BounceInterpolator()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemCountryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.buttonFavorite.setOnCheckedChangeListener(null);
        getItem(position)?.let { country -> holder.bind(country) }
    }

    inner class ViewHolder(val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val cryptoItem = getItem(absoluteAdapterPosition)
            }
        }

        fun bind(item: Country) {
            scaleAnimation.duration = 500
            scaleAnimation.interpolator = bounceInterpolator

            with(binding) {
                root.setOnClickListener {
                    onItemClickListener?.let { navigateDetail -> navigateDetail(item) }
                }
                country = item
                buttonFavorite.isChecked = item.isSaved
                buttonFavorite.setOnCheckedChangeListener(object : View.OnClickListener,
                    CompoundButton.OnCheckedChangeListener {
                    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                        p0?.startAnimation(scaleAnimation);
                        onFavClickListener?.let { databaseOperation -> databaseOperation(item) }
                    }

                    override fun onClick(p0: View?) {}
                });

            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}