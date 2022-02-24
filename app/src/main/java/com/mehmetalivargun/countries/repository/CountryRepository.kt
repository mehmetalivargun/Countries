package com.mehmetalivargun.countries.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mehmetalivargun.countries.api.CountryService
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.data.CountryDetail
import com.mehmetalivargun.countries.data.CountryListResponse
import com.mehmetalivargun.countries.repository.paging.CountryListPagingSource
import com.mehmetalivargun.countries.repository.paging.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRepository @Inject constructor(private val apiService: CountryService) {

    suspend fun getCountryDetails(): Flow<NetworkResult<CountryDetail>> = flow {
        emit(NetworkResult.Loading())
        val response = try {
            apiService.getCountryDetail()
        } catch (ex: Exception) {
            null
        }

        emit(
            when (response?.code()) {
                null -> NetworkResult.Error("text")
                200 -> NetworkResult.Success(response.body()!!.countryDetail)
                else -> NetworkResult.Error("text")

            }
        )
    }

    fun getCountry(): LiveData<PagingData<Country>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CountryListPagingSource(service = apiService)
            }
        ).liveData
    }
/*
    suspend fun getCountryList(): Flow<NetworkResult<List<Country>>> = flow {
        emit(NetworkResult.Loading())
        val response = try {
            apiService.getCountries()
        } catch (ex: Exception) {
            null
        }

        emit(
            when (response?.code()) {
                null -> NetworkResult.Error("text")
                200 -> NetworkResult.Success(response.body()!!.countryList)
                else -> NetworkResult.Error("text")

            }
        )
    }*/


}