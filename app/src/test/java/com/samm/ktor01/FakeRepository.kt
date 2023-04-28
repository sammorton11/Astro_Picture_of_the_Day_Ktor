package com.samm.ktor01

import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.domain.models.Repository

object FakeRepository: Repository {

    override suspend fun getData(): Apod {
        return FakeApodModel.fakeApodModel
    }

    override suspend fun getDataList(count: Int): List<Apod?> {
        return listOf(
            Apod(
                copyright = "copyright",
                date = "date1",
                explanation= "explanation",
                hdUrl = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg",
                mediaType = "image",
                serviceVersion = "v1",
                title = "Title1",
                url = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg"
            ),
            Apod(
                copyright = "copyright",
                date = "date2",
                explanation= "explanation",
                hdUrl = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg",
                mediaType = "image",
                serviceVersion = "v1",
                title = "Title2",
                url = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg"
            ),
            Apod(
                copyright = "copyright",
                date = "date3",
                explanation= "explanation",
                hdUrl = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg",
                mediaType = "image",
                serviceVersion = "v1",
                title = "Title3",
                url = "https://apod.nasa.gov/apod/image/2006/catseye2_not_2048.jpg"
            )
        )
    }

    override suspend fun getDataByDate(date: String): Apod {
        return FakeApodModel.fakeApodModel
    }
}