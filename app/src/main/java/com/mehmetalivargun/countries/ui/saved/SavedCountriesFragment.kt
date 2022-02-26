package com.mehmetalivargun.countries.ui.saved

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.data.db.CountryEntity
import com.mehmetalivargun.countries.data.mappers.toCountryList
import com.mehmetalivargun.countries.databinding.FragmentSavedCountriesBinding
import com.mehmetalivargun.countries.ui.MainViewModel
import com.mehmetalivargun.countries.ui.adapters.CountryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedCountriesFragment :  BaseFragment<FragmentSavedCountriesBinding>(FragmentSavedCountriesBinding::inflate) {

    private val viewModel:MainViewModel by activityViewModels()
    private val adapter = CountryAdapter()

    override fun FragmentSavedCountriesBinding.initialize(savedInstanceState: Bundle?) {
        adapter.onRemoveClickListener={
            viewModel.removeCountry(CountryEntity(it.code,it.name,it.wikiDataId))
        }
        viewModel.savedCountries.observe(viewLifecycleOwner){
            this.recyclerView.adapter=adapter
            adapter.submitList(it.toCountryList())
        }
    }


}