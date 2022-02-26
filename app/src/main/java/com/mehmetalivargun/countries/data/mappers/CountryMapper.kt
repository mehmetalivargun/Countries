package com.mehmetalivargun.countries.data.mappers

import com.mehmetalivargun.countries.data.Country
import com.mehmetalivargun.countries.data.api.CountryResponse
import com.mehmetalivargun.countries.data.db.CountryEntity

fun List<CountryResponse>.toCountryList()= this.map { response->
    response.toCountry()
}

fun CountryResponse.toCountry() = Country(
    code, name, wikiDataId
)

@JvmName("toCountryListCountryDB")
fun List<CountryEntity>.toCountryList()= this.map { response->
    response.toCountry()
}

fun CountryEntity.toCountry() = Country(
    code, name, wikiDataId
)

