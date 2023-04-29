package com.samm.ktor01.data.repository

import android.util.Log
import com.samm.ktor01.core.Constants.BASE_URL
import com.samm.ktor01.core.Constants.BASE_URL_DATE_ENDPOINT
import com.samm.ktor01.core.Constants.BASE_URL_LIST_ENDPOINT
import com.samm.ktor01.data.network.KtorClient
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.domain.models.Repository
import io.ktor.client.request.*

class RepositoryImpl : Repository {


    override suspend fun getData(): Apod {
        return KtorClient.createHttpClient().use { ktor ->
            ktor.get(BASE_URL)
        }
    }

    override suspend fun getDataList(count: Int): List<Apod?> {
        return KtorClient.createHttpClient().use { ktor ->
            ktor.get(BASE_URL_LIST_ENDPOINT(count))
        }
    }

    override suspend fun getDataByDate(date: String): Apod? {
        return try {
            KtorClient.createHttpClient().use { ktor ->
                ktor.get(BASE_URL_DATE_ENDPOINT(date))
            }
        } catch (e: Exception) {
            e.message?.let { Log.e("RepositoryImpl error", it) }
            null
        }
    }
}