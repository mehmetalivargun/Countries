package com.mehmetalivargun.countries.ui.saved

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.data.db.CountryEntity
import com.mehmetalivargun.countries.data.mappers.toCountryList
import com.mehmetalivargun.countries.databinding.FragmentSavedCountriesBinding
import com.mehmetalivargun.countries.ui.MainViewModel
import com.mehmetalivargun.countries.ui.adapters.CountryAdapter
import com.mehmetalivargun.countries.ui.container.HomeContainerFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedCountriesFragment :  BaseFragment<FragmentSavedCountriesBinding>(FragmentSavedCountriesBinding::inflate) {

    private val viewModel:MainViewModel by activityViewModels()
    private val adapter = CountryAdapter()

    override fun FragmentSavedCountriesBinding.initialize(savedInstanceState: Bundle?) {
        adapter.onRemoveClickListener = {
            viewModel.removeCountry(CountryEntity(it.code, it.name, it.wikiDataId))
        }
        adapter.onItemClickListener = { country ->
            val direction =
                HomeContainerFragmentDirections.actionHomeContainerFragmentToDetailFragment(
                    country.code,
                    true
                )
            findNavController().navigate(direction)
        }
        viewModel.savedCountries.observe(viewLifecycleOwner) {

            this.recyclerView.adapter = adapter
            adapter.submitList(it.toCountryList())
        }
    }


}