package com.mehmetalivargun.countries.data


import com.google.gson.annotations.SerializedName

data class CountryListResponse(
    @SerializedName("data")
    val countryList: List<Country>,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("metadata")
    val metadata: Metadata
)