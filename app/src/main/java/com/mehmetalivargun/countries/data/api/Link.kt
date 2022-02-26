package com.mehmetalivargun.countries.data.api


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href")
    val href: String,
    @SerializedName("rel")
    val rel: String
)