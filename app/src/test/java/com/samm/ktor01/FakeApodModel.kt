package com.samm.ktor01

import com.samm.ktor01.domain.models.Apod

object FakeApodModel {

    val fakeApodModel = Apod(
        copyright = "copyright",
        date = "date",
        explanation= "explanation",
        hdUrl = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg",
        mediaType = "image",
        serviceVersion = "v1",
        title = "Title",
        url = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg"
    )
}