package com.mehmetalivargun.countries.repository

import com.mehmetalivargun.countries.api.CountryService
import com.mehmetalivargun.countries.data.api.CountryDetail
import com.mehmetalivargun.countries.db.CountryDAO
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CountryRepository @Inject constructor(private val apiService: CountryService,private val database:CountryDAO) {

    suspend fun getCountryDetails(code:String): Flow<NetworkResult<CountryDetail>> = flow {
        emit(NetworkResult.Loading())
        val response = try {
            apiService.getCountryDetail(code)
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




}