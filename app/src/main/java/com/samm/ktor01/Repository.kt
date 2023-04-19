package com.samm.ktor01

import com.samm.ktor01.Constants.BASE_URL
import io.ktor.client.request.*

object Repository {

    suspend fun getData(): Response {
        return KtorClient.httpClient.use {
            it.get(BASE_URL)
        }
    }
}