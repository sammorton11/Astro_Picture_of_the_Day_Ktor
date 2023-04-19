package com.samm.ktor01.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("date")
    val date: String,
    @SerialName("explanation")
    val explanation: String,
    @SerialName("hdurl")
    val hdUrl: String,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("service_version")
    val serviceVersion: String,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Apod(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("date")
    val date: String,
    @SerialName("explanation")
    val explanation: String,
    @SerialName("hdurl")
    val hdUrl: String,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("service_version")
    val serviceVersion: String,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
)