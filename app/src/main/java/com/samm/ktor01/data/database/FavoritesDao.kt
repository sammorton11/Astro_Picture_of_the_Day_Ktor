package com.samm.ktor01.data.database

import androidx.room.*
import com.samm.ktor01.domain.models.Apod
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM apod")
    fun getAllFavorites(): Flow<List<Apod>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(apod: Apod)

    @Delete
    suspend fun delete(apod: Apod)

    @Query("DELETE FROM apod")
    suspend fun deleteAll()
}