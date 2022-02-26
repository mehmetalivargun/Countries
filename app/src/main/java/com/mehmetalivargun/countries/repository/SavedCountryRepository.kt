package com.mehmetalivargun.countries.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mehmetalivargun.countries.api.CountryService
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.data.api.CountryDetail
import com.mehmetalivargun.countries.data.db.CountryEntity
import com.mehmetalivargun.countries.db.CountryDAO
import com.mehmetalivargun.countries.repository.paging.CountryListPagingSource
import com.mehmetalivargun.countries.repository.paging.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SavedCountryRepository @Inject constructor(private val database: CountryDAO,private val apiService: CountryService) {
    val savedCountries: Flow<List<CountryEntity>> = database.getAll()

    suspend fun insertCountry(item:CountryEntity)=database.insert(item)

    suspend fun deleteCountry(item:CountryEntity)=database.deleteMovie(item)

    suspend fun isExist(code:String) = database.isRowIsExist(code)
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

    fun getCountry(): LiveData<PagingData<Country>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CountryListPagingSource(service = apiService,database=database)
            }
        ).liveData
    }
}