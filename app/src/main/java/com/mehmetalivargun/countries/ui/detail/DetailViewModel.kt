package com.mehmetalivargun.countries.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetalivargun.countries.data.api.CountryDetail
import com.mehmetalivargun.countries.repository.CountryRepository
import com.mehmetalivargun.countries.repository.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel(){
    private val _detail: MutableLiveData<CountryDetail> = MutableLiveData()
    val detail : LiveData<CountryDetail> = _detail

      fun getDetailOfCountry(code:String) = viewModelScope.launch{
        repository.getCountryDetails(code).collect{
                when(it){
                    is NetworkResult.Success -> onSuccess(it.data!!)
                    else-> {}

                }
        }
    }

    private fun onSuccess(data: CountryDetail) {
        Log.e("Test",data.capital)
        _detail.value=data
    }


}