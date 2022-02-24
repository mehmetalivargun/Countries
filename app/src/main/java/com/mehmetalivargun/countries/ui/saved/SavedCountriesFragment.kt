package com.mehmetalivargun.countries.ui.saved

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mehmetalivargun.countries.R
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.databinding.FragmentCountryListBinding
import com.mehmetalivargun.countries.databinding.FragmentSavedCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedCountriesFragment :  BaseFragment<FragmentSavedCountriesBinding>(FragmentSavedCountriesBinding::inflate) {


    override fun FragmentSavedCountriesBinding.initialize(savedInstanceState: Bundle?) {
        Log.e("tes","test")
    }


}