package com.mehmetalivargun.countries.repository.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehmetalivargun.countries.api.CountryService
import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.data.api.CountryResponse
import com.mehmetalivargun.countries.data.mappers.toCountryList
import com.mehmetalivargun.countries.db.CountryDAO
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val NETWORK_PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE = 0

class CountryListPagingSource @Inject constructor(
    private  val service: CountryService,
    private val database: CountryDAO
):PagingSource<Int, Country>()
{
    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        return try {
            val jsonResponse = service.getCountries(offset = offset, limit = 10).body()
            val response = jsonResponse!!.countryResponseList.toCountryList()
            runBlocking {
                //block the current coroutine and check the countries if they are saved to database
                response.map {
                    it.isSaved=database.isRowIsExist(it.code)
                }
            }

            val nextKey = if (response.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}