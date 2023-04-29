package com.samm.ktor01.domain.repository

import com.samm.ktor01.domain.models.Apod

interface Repository {
    suspend fun getData(): Apod
    suspend fun getDataList(count: Int): List<Apod?>
    suspend fun getDataByDate(date: String): Apod?
}