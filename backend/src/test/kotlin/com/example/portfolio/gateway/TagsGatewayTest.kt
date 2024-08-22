package com.example.portfolio.gateway

import com.example.ktknowledgeTodo.infra.jooq.tables.records.TagsRecord
import com.example.portfolio.domain.*
import com.example.portfolio.driver.TagsDriver
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDateTime

internal class TagsGatewayTest {
    @InjectMockKs
    private lateinit var tagsGateway: TagsGateway

    @BeforeEach
    fun setUp() {
        tagsGateway = TagsGateway()
        tagsDriver = Mockito.mock(TagsDriver::class.java)
        MockKAnnotations.init(this)
    }

    @MockK
    lateinit var tagsDriver: TagsDriver

    @Test
    fun getTagsTest() {
        val dictionaryId = DictionaryId(1)
        val tagsRecords = listOf(TagsRecord(1, "test1", LocalDateTime.now()))
        val expected = Tags(listOf(Tag(TagId(1), TagName("test1"))))

        every { tagsDriver.findSelectedTagByIndexId(any()) } returns tagsRecords

        val actual = tagsGateway.getSelectedTags(dictionaryId)
        assertThat(expected, `is`(actual))

        verify { tagsDriver.findSelectedTagByIndexId(1) }
    }

    @Test
    fun getAllTagsTest() {
        val expected = Tags(listOf(Tag(TagId(1), TagName("test"))))
        val tagsRecords = listOf(TagsRecord(1, "test", LocalDateTime.now()))

        every { tagsDriver.getAllTags() } returns tagsRecords

        val actual = tagsGateway.getAllTags()
        assertThat(expected, `is`(actual))

        verify { tagsDriver.getAllTags() }
    }

    @Test
    fun updateTagsTest() {
        val dictionaryId = DictionaryId(1)
        val tagIds = TagIds(listOf(TagId(10), TagId(20)))

        every { tagsDriver.deleteTagsRelationMap(any()) } just runs
        every { tagsDriver.insertTagRelationMap(any(), any()) } just runs

        tagsGateway.updateTags(dictionaryId, tagIds)

        verify { tagsDriver.deleteTagsRelationMap(1) }
        verify { tagsDriver.insertTagRelationMap(1, listOf(10, 20)) }
    }

    @Test
    fun insertTagTest() {
        val tagName = TagName("newTagName")
        val expected = 2

        every { tagsDriver.insertTag(any()) } returns expected

        val actual = tagsGateway.insertTag(tagName)
        assertThat(expected, `is`(actual))

        verify { tagsDriver.insertTag("newTagName") }
    }
}