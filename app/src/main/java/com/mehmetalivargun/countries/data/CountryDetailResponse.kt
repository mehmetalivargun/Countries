package com.mehmetalivargun.countries.data


import com.google.gson.annotations.SerializedName

data class CountryDetailResponse(
    @SerializedName("data")
    val countryDetail: CountryDetail
)