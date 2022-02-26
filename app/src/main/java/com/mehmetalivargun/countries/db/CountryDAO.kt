package com.mehmetalivargun.countries.db

import androidx.room.*
import com.mehmetalivargun.countries.data.db.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDAO {

    @Query("SELECT * FROM country")
     fun getAll(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM country WHERE code = :code")
    suspend fun getById(code: String): CountryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity)

    @Delete
    suspend fun deleteMovie(movie: CountryEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM country WHERE code = :code)")
    suspend fun isRowIsExist(code : String) : Boolean

}