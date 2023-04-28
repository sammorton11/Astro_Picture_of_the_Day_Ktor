package com.samm.ktor01

import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AstroViewModelTest {

    private val repository = FakeRepository
    private val repositoryMock = mockk<FakeRepository>(relaxed = true)

    @Test
    fun test_getData() = runBlocking {
        val result = repository.getData()
        assert(result.url != null)
        assert(result.hdUrl != null)
        assert(result.date != null)
        assert(result.explanation != null)
        assert(result.mediaType != null)
        assert(result.title != null)
        assert(result.serviceVersion != null)
    }

    @Test
    fun test_getData_mock() = runBlocking {
        val result = repositoryMock.getData()

        assert(result.url != null)
        assert(result.hdUrl != null)
        assert(result.date != null)
        assert(result.explanation != null)
        assert(result.mediaType != null)
        assert(result.title != null)
        assert(result.serviceVersion != null)
    }
}
