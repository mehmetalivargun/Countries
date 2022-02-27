package com.mehmetalivargun.countries.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.ToggleButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mehmetalivargun.countries.R
import com.mehmetalivargun.countries.base.BaseFragment
import com.mehmetalivargun.countries.data.api.CountryDetail
import com.mehmetalivargun.countries.data.db.CountryEntity
import com.mehmetalivargun.countries.databinding.FragmentDetailBinding
import com.mehmetalivargun.countries.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    val viewModel: MainViewModel by activityViewModels()
    val args: DetailFragmentArgs by navArgs()

    var country: CountryDetail? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)

        val favButton = menu.findItem(R.id.favButtonAction)
        favButton.setActionView(R.layout.use_toggle)
        val fav =
            menu.findItem(R.id.favButtonAction).actionView.findViewById<ToggleButton>(R.id.buttonFavOnDetail)

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
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator

        fav.isChecked = args.isSaved
        fav.setOnCheckedChangeListener(object : View.OnClickListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                p0?.startAnimation(scaleAnimation)
                if (args.isSaved && country != null) {
                    viewModel.removeCountry(
                        CountryEntity(
                            country!!.code,
                            country!!.name,
                            country!!.wikiDataId
                        )
                    )
                } else {
                    viewModel.insertCountry(
                        CountryEntity(
                            country!!.code,
                            country!!.name,
                            country!!.wikiDataId
                        )
                    )
                }
            }

            override fun onClick(p0: View?) {}
        })
    }


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
        val code = args.code

        if (code != "-1") {
            this@DetailFragment.viewModel.getDetailOfCountry(code)
        }
        this@DetailFragment.viewModel.detail.observe(viewLifecycleOwner) { country ->

            this@DetailFragment.country = country
            setupLayout(country)
        }
    }

    fun setupLayout(country: CountryDetail?) {
        if (country != null) {
            binding.detailLayout.visibility = View.VISIBLE
            binding.detailProgress.visibility = View.GONE
            requireActivity().invalidateOptionsMenu()
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