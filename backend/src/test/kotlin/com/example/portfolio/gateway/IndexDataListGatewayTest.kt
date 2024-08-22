package com.example.portfolio.gateway

import com.example.ktknowledgeTodo.infra.jooq.tables.records.DictionaryRecord
import com.example.portfolio.domain.*
import com.example.portfolio.driver.IndexDataListDriver
import com.example.portfolio.driver.StorageDriver
import com.example.portfolio.driver.TagsDriver
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

class IndexDataListGatewayTest {

    @InjectMockKs
    private lateinit var indexDataListGateway: IndexDataListGateway

    @BeforeEach
    fun setUp() {
        indexDataListGateway = IndexDataListGateway()
        indexDataListDriver = Mockito.mock(IndexDataListDriver::class.java)
        MockKAnnotations.init(this)
    }

    @MockK
    lateinit var indexDataListDriver: IndexDataListDriver

    @MockK
    lateinit var storageDriver: StorageDriver

    @MockK
    lateinit var tagsDriver: TagsDriver

    @Test
    fun getIndexDataListTest() {
        val encyclopediaRecords = listOf(DictionaryRecord(1, "testName", "testDescription"))
        val expected = IndexDataList(listOf(IndexData(DictionaryId(1), Name("testName"), Description("testDescription"), null, null)))

        every { indexDataListDriver.findIndexDataList() } returns encyclopediaRecords

        val result = indexDataListGateway.getIndexDataList()
        assertThat(expected, `is`(result))

        verify { indexDataListDriver.findIndexDataList() }
    }

    @Test
    fun registerIndexTest() {
        val name = Name("test-name")
        val description = Description("test-description")
        val image = ImageFile(mockk())
        val indexId = 1
        val tags = Tags(
            listOf(
                Tag(TagId(0), TagName("newTag")),
                Tag(TagId(0), TagName("newTag2")),
                Tag(TagId(1), TagName("registeredTag"))
            )
        )
        val newTagId = 2
        val newTagId2 = 3

        every { indexDataListDriver.insertIndexDataList(any(), any()) } returns indexId
        every { tagsDriver.insertTag("newTag") } returns newTagId
        every { tagsDriver.insertTag("newTag2") } returns newTagId2
        every { tagsDriver.insertTagRelationMap(any(), any()) } just runs
        every { storageDriver.storeImage(any(), any()) } just runs

        indexDataListGateway.registerIndex(name, description, image, tags)

        verify { storageDriver.storeImage(any(), indexId) }
        verify { tagsDriver.insertTag("newTag") }
        verify { tagsDriver.insertTag("newTag2") }
        verify { tagsDriver.insertTagRelationMap(indexId, listOf(2, 3, 1)) }
        verify { indexDataListDriver.insertIndexDataList("test-name", "test-description") }
    }

    @Test
    fun searchByKeywordsTest() {
        val keywordSearchCondition = KeywordSearchCondition(
            Keywords(listOf(Keyword("test1"), Keyword("test2"))),
            OrCondition(true)
        )

        val encyclopediaRecords = listOf(DictionaryRecord(1, "testName", "testDescription"))
        val expected = IndexDataList(listOf(IndexData(DictionaryId(1), Name("testName"), Description("testDescription"), null, null)))

        every { indexDataListDriver.searchByKeywords(any(), any()) } returns encyclopediaRecords

        val actual = indexDataListGateway.searchByKeywords(keywordSearchCondition)
        assertThat(expected, `is`(actual))

        verify { indexDataListDriver.searchByKeywords(listOf("test1", "test2"), true) }
    }

    @Test
    fun searchByTagsTest() {
        val tagSearchCondition = TagSearchCondition(
            TagIds(listOf(TagId(1))), OrCondition(true)
        )
        val encyclopediaRecords = listOf(DictionaryRecord(1, "testName", "testDescription"))
        val expected = IndexDataList(listOf(IndexData(DictionaryId(1), Name("testName"), Description("testDescription"), null, null)))

        every { indexDataListDriver.findByTagIds(any(), any()) } returns encyclopediaRecords

        val actual = indexDataListGateway.searchByTags(tagSearchCondition)
        assertThat(expected, `is`(actual))

        verify { indexDataListDriver.findByTagIds(listOf(1), true) }
    }

    @Test
    fun deleteIndexTest() {
        val dictionaryId = DictionaryId(1)

        every { tagsDriver.deleteTagsRelationMap(any()) } just runs
        every { indexDataListDriver.deleteById(any()) } just runs

        indexDataListGateway.deleteIndex(dictionaryId)

        verify { tagsDriver.deleteTagsRelationMap(1) }
        verify { indexDataListDriver.deleteById(1) }
    }

    @Test
    fun updateDictionary() {
        val dictionaryId = DictionaryId(1)
        val name = Name("testName")
        val description = Description("testDescription")

        every { indexDataListDriver.updateDictionary(any(), any(), any()) } just runs

        indexDataListGateway.updateDictionary(dictionaryId, name, description)

        verify { indexDataListDriver.updateDictionary(1, "testName", "testDescription") }
    }
}