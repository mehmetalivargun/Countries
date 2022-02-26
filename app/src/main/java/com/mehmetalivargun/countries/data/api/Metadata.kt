package com.mehmetalivargun.countries.data.api


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentOffset")
    val currentOffset: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)