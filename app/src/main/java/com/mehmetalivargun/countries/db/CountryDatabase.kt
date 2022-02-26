package com.mehmetalivargun.countries.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehmetalivargun.countries.data.db.CountryEntity

@Database(entities = [CountryEntity::class],version = 1,exportSchema = false)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDAO():CountryDAO
}