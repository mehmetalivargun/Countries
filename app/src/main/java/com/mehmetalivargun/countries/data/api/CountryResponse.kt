package com.mehmetalivargun.countries.data.api


import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("currencyCodes")
    val currencyCodes: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("wikiDataId")
    val wikiDataId: String
)