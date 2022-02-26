package com.mehmetalivargun.countries.data.api


import com.google.gson.annotations.SerializedName

data class CountryDetailResponse(
    @SerializedName("data")
    val countryDetail: CountryDetail
)