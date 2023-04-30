package com.samm.ktor01.domain.repository

import com.samm.ktor01.domain.models.Apod
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getData(): Apod
    suspend fun getDataList(count: Int): List<Apod?>
    suspend fun getDataByDate(date: String): Apod?

    fun getAllFavorites(): Flow<List<Apod>>
    suspend fun insertFavorite(apod: Apod)
    suspend fun deleteFavorite(apod: Apod)
}