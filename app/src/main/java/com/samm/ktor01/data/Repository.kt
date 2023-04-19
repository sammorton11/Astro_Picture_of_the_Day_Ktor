package com.samm.ktor01.data

import com.samm.ktor01.domain.Response
import com.samm.ktor01.core.Constants
import com.samm.ktor01.core.Constants.BASE_URL
import com.samm.ktor01.domain.Apod
import io.ktor.client.request.*

object Repository {
    suspend fun getData(): Response {
        return KtorClient.httpClient.use {
            it.get(BASE_URL)
        }
    }
    suspend fun getDataList(count: Int): List<Apod?> {
        return KtorClient.httpClient.use {
            it.get("https://api.nasa.gov/planetary/apod?api_key=${Constants.API_KEY}&count=$count")
        }
    }
}