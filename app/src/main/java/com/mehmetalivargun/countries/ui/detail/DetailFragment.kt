package com.mehmetalivargun.countries.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.data.api.CountryDetail
import com.mehmetalivargun.countries.databinding.FragmentDetailBinding
import com.mehmetalivargun.countries.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun FragmentDetailBinding.initialize(savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = this@DetailFragment.viewModel
        val args: DetailFragmentArgs by navArgs()
        val code = args.code

        if (code != null) {
            this@DetailFragment.viewModel.getDetailOfCountry(code)
        }
        this@DetailFragment.viewModel.detail.observe(viewLifecycleOwner) { country ->
            setupLayout(country)
        }
    }

    fun setupLayout(country: CountryDetail?) {
        if (country != null) {
            binding.detailLayout.visibility = View.VISIBLE
            binding.detailProgress.visibility = View.GONE
        }
        binding.wikiNavigateButton.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.wikidata.org/wiki/" + country?.wikiDataId)
            )
            startActivity(browserIntent)
        }
    }

    override fun onDestroy() {
        viewModel.clearDetails()
        super.onDestroy()
    }


}