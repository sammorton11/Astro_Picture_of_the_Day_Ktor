package com.samm.ktor01.data.repository

import android.util.Log
import com.samm.ktor01.core.Constants
import com.samm.ktor01.core.Constants.BASE_URL
import com.samm.ktor01.data.network.KtorClient
import com.samm.ktor01.domain.models.Apod
import io.ktor.client.request.*
import io.ktor.utils.io.*

object Repository {

    suspend fun getData(): Apod {
        return KtorClient.createHttpClient().use {
            it.get(BASE_URL)
        }
    }

    suspend fun getDataList(count: Int): List<Apod?> {
        return KtorClient.createHttpClient().use {
            it.get("https://api.nasa.gov/planetary/apod?api_key=${Constants.API_KEY}&count=$count")
        }
    }

    suspend fun getDataByDate(date: String): Apod? {
        return try {
            KtorClient.createHttpClient().use {
                it.get("https://api.nasa.gov/planetary/apod?api_key=${Constants.API_KEY}&date=$date")
            }
        } catch (e: CancellationException) {
            e.message?.let { Log.e("Repository error", it) }
            null // or some other appropriate response
        }
    }
}