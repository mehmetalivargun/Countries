package com.mehmetalivargun.countries.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.data.api.CountryDetail
import com.mehmetalivargun.countries.data.db.CountryEntity
import com.mehmetalivargun.countries.repository.SavedCountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: SavedCountryRepository) : ViewModel() {

    val savedCountries: LiveData<List<CountryEntity>> = repository.savedCountries.asLiveData()
    val saveState:MutableLiveData<Pair<String,Boolean>> = MutableLiveData()



    fun getCountryListPaged(): LiveData<PagingData<Country>> {
        return repository.getCountry().cachedIn(viewModelScope)
    }

    fun insertCountry(item: CountryEntity)= viewModelScope.launch {
        repository.insertCountry(item)
    }

    fun  isSaved(code:String) = viewModelScope.launch {
        val test = repository.isExist(code)
    }

    fun removeCountry(item: CountryEntity) = viewModelScope.launch {
        repository.deleteCountry(item)
    }

    private val _detail: MutableLiveData<CountryDetail> = MutableLiveData()
    val detail : LiveData<CountryDetail> = _detail


}