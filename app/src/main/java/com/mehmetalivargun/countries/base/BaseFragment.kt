package com.mehmetalivargun.countries.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment <DB : ViewDataBinding>(
    private val inflate: Inflate<DB>
) : Fragment() {
    private var _binding: DB? = null
    val binding get() = _binding!!

    open fun DB.initialize(savedInstanceState: Bundle?){}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.initialize(savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}