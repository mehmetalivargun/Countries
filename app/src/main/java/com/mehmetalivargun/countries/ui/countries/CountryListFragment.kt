package com.mehmetalivargun.countries.ui.countries

import android.os.Bundle

import androidx.fragment.app.viewModels
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.databinding.FragmentCountryListBinding
import com.mehmetalivargun.countries.ui.adapters.CountryAdapter
import com.mehmetalivargun.countries.ui.adapters.CountryVAdapter
import com.mehmetalivargun.countries.ui.adapters.PagingLoadStateAdapter
import com.mehmetalivargun.countries.ui.countries.DiffUtil.searchResultDiffUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : BaseFragment<FragmentCountryListBinding>(FragmentCountryListBinding::inflate) {
    private var searchAdapter: CountryVAdapter = CountryVAdapter()

    private val viewModel:CountryListViewModel by viewModels ()

    override fun FragmentCountryListBinding.initialize(savedInstanceState: Bundle?) {
        binding.lifecycleOwner=viewLifecycleOwner
        binding.viewModel=this@CountryListFragment.viewModel
        viewModel?.getCountryListPaged()?.observe(viewLifecycleOwner) {
            searchAdapter.submitData(lifecycle, it)
           // binding.recyclerView.adapter = searchAdapter

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




}

object DiffUtil {
    val searchResultDiffUtil: DiffUtil.ItemCallback<Country> =object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: Country, newItem: Country) =
            oldItem == newItem
    }
}