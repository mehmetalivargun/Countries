package com.mehmetalivargun.countries.ui.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mehmetalivargun.countries.R
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.databinding.FragmentHomeContainerBinding
import com.mehmetalivargun.countries.ui.countries.CountryListFragment
import com.mehmetalivargun.countries.ui.saved.SavedCountriesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeContainerFragment : BaseFragment<FragmentHomeContainerBinding>(FragmentHomeContainerBinding::inflate) {

    private val countryListFragment: CountryListFragment by lazy { CountryListFragment() }
    private val savedCountriesFragment: SavedCountriesFragment by lazy { SavedCountriesFragment() }
    private var activeFragment: Fragment? = null

    override fun FragmentHomeContainerBinding.initialize(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragments()
            setUpBottomNavItemSelectedListener()
        }
    }

    private fun addFragments() {
        if (childFragmentManager.fragments.isEmpty()) {
            // Set Up the active fragment
            activeFragment = countryListFragment
            // Add fragments to the FragmentContainer
            childFragmentManager.beginTransaction()
                .add(R.id.homeContainerView, savedCountriesFragment, "ActorsListFragment")
                .hide(savedCountriesFragment)
                .add(R.id.homeContainerView, countryListFragment, "MovieListFragment")
                .commit()
        }
    }

    private fun setUpBottomNavItemSelectedListener() {
        binding.bottomNavHome
            .setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        childFragmentManager.beginTransaction()
                            .hide(activeFragment!!)
                            .show(countryListFragment)
                            .commit()
                        activeFragment = countryListFragment
                        return@setOnItemSelectedListener true
                    }
                    R.id.nav_saved -> {
                        childFragmentManager.beginTransaction()
                            .hide(activeFragment!!)
                            .show(savedCountriesFragment)
                            .commit()
                        activeFragment = savedCountriesFragment
                        return@setOnItemSelectedListener true
                    }
                }
                return@setOnItemSelectedListener false
            }
    }


}