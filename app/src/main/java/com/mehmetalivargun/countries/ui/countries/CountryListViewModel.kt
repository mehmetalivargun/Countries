package com.mehmetalivargun.countries.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.repository.CountryRepository
import com.mehmetalivargun.countries.repository.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel(){

    private val _countryList = MutableLiveData<List<Country>>()
    val countryList: LiveData<List<Country>> = _countryList

 //

     fun getCountryListPaged() :LiveData<PagingData<Country>>{
        val result = repository.getCountry().cachedIn(viewModelScope)
        return  result

    }


/*
    private fun getCountryList() = viewModelScope.launch {
        repository.getCountryList().collect {
            when(it){
                is NetworkResult.Success-> onSuccess(it)
                else-> onError()
            }
        }
    }*/

    private fun onError() {

    }

    private fun onSuccess(it: NetworkResult.Success<List<Country>>) {
        _countryList.value=it.data!!
    }


}