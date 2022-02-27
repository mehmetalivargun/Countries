package com.mehmetalivargun.countries.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.data.api.CountryDetail
import com.mehmetalivargun.countries.data.db.CountryEntity
import com.mehmetalivargun.countries.repository.NetworkResult
import com.mehmetalivargun.countries.repository.SavedCountryRepository
import com.mehmetalivargun.countries.util.expand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: SavedCountryRepository) : ViewModel() {

    val savedCountries: LiveData<List<CountryEntity>> = repository.savedCountries.asLiveData()

    private val _detail: MutableLiveData<CountryDetail?> = MutableLiveData()
    val detail: LiveData<CountryDetail?> = _detail


    private val _image: MutableLiveData<String> = MutableLiveData()
    val image: LiveData<String> = _image

    val isSaved: MutableLiveData<Boolean> = MutableLiveData()


    fun getCountryListPaged(): LiveData<PagingData<Country>> {
        return repository.getCountry().cachedIn(viewModelScope)
    }

    fun insertCountry(item: CountryEntity) = viewModelScope.launch {
        repository.insertCountry(item)
    }

    fun isSaved(code: String) = viewModelScope.launch {
        isSaved.postValue(repository.isExist(code))
    }

    fun removeCountry(item: CountryEntity) = viewModelScope.launch {
        repository.deleteCountry(item)
    }

    fun getDetailOfCountry(code: String) = viewModelScope.launch {
        repository.getCountryDetails(code).collect {
            when (it) {
                is NetworkResult.Success -> onSuccess(it.data!!)
                else -> {
                }
            }
        }
    }

    fun clearDetails() {
        _detail.value = null
    }

    fun onSuccess(data: CountryDetail) {
        _detail.value = data

        GlobalScope.launch(Dispatchers.IO) {
            _image.postValue(expand(data.flagImageUri)!!)
        }

    }


}