package com.samm.ktor01.data.network

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

object KtorClient {

    private val json = Json {
        isLenient = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    fun createHttpClient(): HttpClient {
        return HttpClient {

            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }

            install(Logging) {
                logger = object: Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 30000
                connectTimeoutMillis = 30000
                requestTimeoutMillis = 30000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}