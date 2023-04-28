package com.samm.ktor01.core

object Constants {
    const val API_KEY = "qGqHQYIgYmjxAKCaFJHaN9I3XTvpbHQHS8N7yMNO"
    const val BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=qGqHQYIgYmjxAKCaFJHaN9I3XTvpbHQHS8N7yMNO"
    val BASE_URL_LIST_ENDPOINT = fun(count: Int) = "https://api.nasa.gov/planetary/apod?api_key=$API_KEY&count=$count"
    val BASE_URL_DATE_ENDPOINT = fun(date: String) = "https://api.nasa.gov/planetary/apod?api_key=${API_KEY}&date=$date"
}

