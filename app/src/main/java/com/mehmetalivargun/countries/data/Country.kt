package com.mehmetalivargun.countries.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Country(
    val code: String,
    val name: String,
    val wikiDataId: String,
    var isSaved: Boolean=false
)
