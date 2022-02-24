package com.mehmetalivargun.countries.api

import com.mehmetalivargun.countries.data.CountryDetailResponse
import com.mehmetalivargun.countries.data.CountryListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {

    @GET("https://wft-geo-db.p.rapidapi.com/v1/geo/countries?")
    suspend fun getCountries(@Query("offset") offset:Int, @Query("limit") limit:Int=10): Response<CountryListResponse>

    @GET("https://wft-geo-db.p.rapidapi.com/v1/geo/countries/US")
    suspend fun getCountryDetail():Response<CountryDetailResponse>
}