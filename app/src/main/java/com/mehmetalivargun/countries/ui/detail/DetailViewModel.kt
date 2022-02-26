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
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel() {
    private val _detail: MutableLiveData<CountryDetail> = MutableLiveData()
    val detail: LiveData<CountryDetail> = _detail

    private val _image: MutableLiveData<String> = MutableLiveData()
    val image: LiveData<String> = _image

    fun getDetailOfCountry(code: String) = viewModelScope.launch {
        repository.getCountryDetails(code).collect {
            when (it) {
                is NetworkResult.Success -> onSuccess(it.data!!)
                else -> {
                }
            }
        }
    }

    private fun onSuccess(data: CountryDetail) {
        _detail.value = data
        _image.value = data.flagImageUri
    }


    private fun test(shortURL: String): String {
        val urlConn = connectURL(shortURL)



        Log.e("Original URL: ", urlConn?.headerFields?.get("Location").toString())
        return urlConn?.url.toString()

    }

    fun connectURL(strUrl: String): URLConnection? {
        var con: URLConnection? = null
        try {
            val inputUrl = URL(strUrl)
            con = inputUrl.openConnection()
            var test = 0
        } catch (e: MalformedURLException) {
            Log.e("E", e.toString())
        } catch (ex: IOException) {
            Log.e("ex", ex.toString())
        }
        return con

    }


}