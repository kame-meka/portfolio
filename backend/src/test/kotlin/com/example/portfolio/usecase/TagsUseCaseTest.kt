package com.example.portfolio.usecase

import com.example.portfolio.domain.Tag
import com.example.portfolio.domain.TagId
import com.example.portfolio.domain.TagName
import com.example.portfolio.domain.Tags
import com.example.portfolio.port.IndexDataListPort
import com.example.portfolio.port.StoragePort
import com.example.portfolio.port.TagsPort
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TagsUseCaseTest {
    @InjectMockKs
    private lateinit var tagsUseCase: TagsUseCase

    @BeforeEach
    fun setUp() {
        tagsUseCase = TagsUseCase()
        tagsPort = Mockito.mock(TagsPort::class.java)
        MockKAnnotations.init(this)
    }

    @MockK
    lateinit var tagsPort: TagsPort

    @Test
    fun getAllTagsTest() {
        val expected = Tags(listOf(Tag(TagId(1), TagName("aaa"))))

        every { tagsPort.getAllTags() } returns expected

        val actual = tagsUseCase.getAllTags()
        assertThat(expected, `is`(actual))

        verify { tagsPort.getAllTags() }
    }

}