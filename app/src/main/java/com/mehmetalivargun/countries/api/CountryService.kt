package com.mehmetalivargun.countries.api

import com.mehmetalivargun.countries.data.api.CountryDetailResponse
import com.mehmetalivargun.countries.data.api.CountryListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryService {

    @GET("https://wft-geo-db.p.rapidapi.com/v1/geo/countries?")
    suspend fun getCountries(@Query("offset") offset:Int, @Query("limit") limit:Int=10): Response<CountryListResponse>

    @GET("https://wft-geo-db.p.rapidapi.com/v1/geo/countries/{code}")
    suspend fun getCountryDetail(@Path("code") code :String):Response<CountryDetailResponse>
}