package com.mehmetalivargun.countries.data.api


import com.google.gson.annotations.SerializedName

data class CountryListResponse(
    @SerializedName("data")
    val countryResponseList: List<CountryResponse>,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("metadata")
    val metadata: Metadata
)