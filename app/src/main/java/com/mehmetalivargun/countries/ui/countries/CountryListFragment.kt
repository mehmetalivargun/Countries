package com.mehmetalivargun.countries.ui.countries

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels

import androidx.navigation.fragment.findNavController
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.data.db.CountryEntity
import com.mehmetalivargun.countries.databinding.FragmentCountryListBinding
import com.mehmetalivargun.countries.ui.MainViewModel
import com.mehmetalivargun.countries.ui.adapters.CountryPagingAdapter
import com.mehmetalivargun.countries.ui.adapters.PagingLoadStateAdapter
import com.mehmetalivargun.countries.ui.container.HomeContainerFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : BaseFragment<FragmentCountryListBinding>(FragmentCountryListBinding::inflate) {
    private var searchAdapter: CountryPagingAdapter = CountryPagingAdapter()

    private val viewModel:MainViewModel by activityViewModels()


    override fun FragmentCountryListBinding.initialize(savedInstanceState: Bundle?) {
        binding.lifecycleOwner=viewLifecycleOwner
        viewModel.getCountryListPaged().observe(viewLifecycleOwner) {

            searchAdapter.submitData(lifecycle, it)
            setItemListeners()
            with(binding){
                recyclerView.adapter=searchAdapter
                recyclerView.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
            }
            binding.recyclerView.adapter = searchAdapter.withLoadStateFooter(
                footer = PagingLoadStateAdapter(searchAdapter)
            )
        }

    }


    private fun setItemListeners(){
        searchAdapter.onFavClickListener={ country->
            if (country.isSaved){
                this@CountryListFragment.viewModel.removeCountry(CountryEntity(country.code,country.name,country.wikiDataId))
                country.isSaved=false

            }else{
                this@CountryListFragment.viewModel.insertCountry(CountryEntity(country.code,country.name,country.wikiDataId))
                country.isSaved=true
            }
        }
        searchAdapter.onItemClickListener={
            viewModel.isSaved(it.code)
            val direction = HomeContainerFragmentDirections.actionHomeContainerFragmentToDetailFragment(it.code)
            findNavController().navigate(direction)
        }
    }




}

