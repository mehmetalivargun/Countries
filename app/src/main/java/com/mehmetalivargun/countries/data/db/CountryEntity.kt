package com.mehmetalivargun.countries.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey val code: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "wikiDataId")
    val wikiDataId: String
)