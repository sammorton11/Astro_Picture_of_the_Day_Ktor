package com.samm.ktor01.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Entity
@Serializable
data class Apod(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerialName("copyright")
    val copyright: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("explanation")
    val explanation: String? = null,
    @SerialName("hdurl")
    @JvmField
    val hdUrl: String? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    @SerialName("service_version")
    val serviceVersion: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("BASE_URL_LIST_ENDPOINT")
    val url: String? = null
)
