package com.samm.ktor01.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.samm.ktor01.domain.models.Apod
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM apod")
    fun getAllFavorites(): Flow<List<Apod>>

    @Insert
    fun insertFavorite(apod: Apod)

    @Delete
    fun delete(apod: Apod)

    @Query("DELETE FROM apod")
    fun deleteAll()
}