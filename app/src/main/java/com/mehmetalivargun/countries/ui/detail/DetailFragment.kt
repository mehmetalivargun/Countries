package com.mehmetalivargun.countries.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mehmetalivargun.countries.R
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    val viewModel : DetailViewModel by viewModels()

    override fun FragmentDetailBinding.initialize(savedInstanceState: Bundle?) {
        binding.lifecycleOwner=viewLifecycleOwner
        binding.viewModel=this@DetailFragment.viewModel

        this@DetailFragment.viewModel.getDetailOfCountry("AL")



    }

    fun getHTMLBody(svgString: String) = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
    "<body>\n" +
    "    <div id=\"div\" class=\"container\">\n" +
    "\t\n" +
    svgString +
    "\n" +
    "    <script src=\"index.js\"></script>\n" +
    "</body>\n" +
    "</html>"



}