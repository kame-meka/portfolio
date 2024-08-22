package com.example.portfolio.gateway

import com.example.portfolio.domain.DisplayImageFile
import com.example.portfolio.domain.DictionaryId
import com.example.portfolio.domain.ImageFile
import com.example.portfolio.driver.StorageDriver
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class StorageGatewayTest {
    @InjectMockKs
    private lateinit var storageGateway: StorageGateway

    @BeforeEach
    fun setUp() {
        storageGateway = StorageGateway()
        storageDriver = Mockito.mock(StorageDriver::class.java)
        MockKAnnotations.init(this)
    }

    @MockK
    lateinit var storageDriver: StorageDriver

    @Test
    fun getImageFileTest() {
        val dictionaryId = DictionaryId(1)
        val byteArray = ByteArray(10)
        val expected = DisplayImageFile(byteArray)

        every { storageDriver.getImageFileByName(any()) } returns byteArray

        val actual = storageGateway.getImageFile(dictionaryId)
        assertThat(actual, `is`(expected))

        verify { storageDriver.getImageFileByName("1") }
    }

    @Test
    fun deleteImageTest() {
        val dictionaryId = DictionaryId(1)

        every { storageDriver.deleteById(any()) } just runs

        storageGateway.deleteImage(dictionaryId)

        verify { storageDriver.deleteById(1) }
    }

    @Test
    fun updateImageTest() {
        val dictionaryId = DictionaryId(1)
        val imageFile = ImageFile(mockk())

        every { storageDriver.deleteById(any()) } just runs
        every { storageDriver.storeImage(any(), any()) } just runs

        storageGateway.updateImage(dictionaryId, imageFile)

        verify { storageDriver.deleteById(1) }
        verify { storageDriver.storeImage(any(), 1) }

    }
}