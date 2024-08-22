package com.example.portfolio.usecase

import com.example.portfolio.domain.*
import com.example.portfolio.port.IndexDataListPort
import com.example.portfolio.port.StoragePort
import com.example.portfolio.port.TagsPort
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class IndexDataListUseCaseTest {

    @InjectMockKs
    private lateinit var indexDataListUseCase: IndexDataListUseCase

    @BeforeEach
    fun setUp() {
        indexDataListUseCase = IndexDataListUseCase()
        indexDataListPort = mock(IndexDataListPort::class.java)
        storagePort = mock(StoragePort::class.java)
        MockKAnnotations.init(this)
    }

    @MockK
    lateinit var indexDataListPort: IndexDataListPort

    @MockK
    lateinit var storagePort: StoragePort

    @MockK
    lateinit var tagsPort: TagsPort

    @Test
    fun getIndexDataListTest() {
        val indexDataList = IndexDataList(
            listOf(IndexData(DictionaryId(1), Name(""), Description(""), null, null))
        )
        val imageFile = ByteArray(10)
        val tags = Tags(listOf(Tag(TagId(1), TagName("testTag"))))
        val expected = IndexDataList(
            listOf(IndexData(DictionaryId(1), Name(""), Description(""), DisplayImageFile(imageFile), tags))
        )

        every { indexDataListPort.getIndexDataList() } returns indexDataList
        every { storagePort.getImageFile(any()) } returns DisplayImageFile(imageFile)
        every { tagsPort.getSelectedTags(any()) } returns tags

        val result = indexDataListUseCase.getIndexDataList()
        assertThat(expected, `is`(result))

        verify { indexDataListPort.getIndexDataList() }
        verify { storagePort.getImageFile(any()) }
        verify { tagsPort.getSelectedTags(DictionaryId(1)) }
    }

    @Test
    fun registerIndexTest() {
        val name = Name("test-name")
        val description = Description("test-description")
        val imageFile = ImageFile(mockk())
        //val tagIds = TagIds(listOf(TagId(1), TagId(2)))
        val tags = Tags(listOf(Tag(TagId(1), TagName("tagName"))))

        every { indexDataListPort.registerIndex(any(), any(), any(), any()) } just runs

        indexDataListUseCase.registerIndex(name, description, imageFile, tags)

        verify { indexDataListPort.registerIndex(name, description, imageFile, tags) }
    }

    @Test
    fun searchIndexDataListTest() {
        val keywords = Keywords(listOf(Keyword("test"), Keyword("test2")))
        val tagIds = TagIds(listOf(TagId(1)))
        val keywordSearchOrConditionTag = OrCondition(true)
        val tagSearchOrConditionTag = OrCondition(true)
        val keywordSearchCondition = KeywordSearchCondition(keywords, keywordSearchOrConditionTag)
        val tagSearchCondition = TagSearchCondition(tagIds, tagSearchOrConditionTag)

        val searchedByKeywords = IndexDataList(
            listOf(
                IndexData(DictionaryId(1), Name("testName1"), Description("testDescription1"), null, null),
                IndexData(DictionaryId(2), Name("testName2"), Description("testDescription2"), null, null)
            )
        )
        val searchedByTags = IndexDataList(
            listOf(
                IndexData(DictionaryId(2), Name("testName2"), Description("testDescription2"), null, null),
                IndexData(DictionaryId(3), Name("testName3"), Description("testDescription3"), null, null)
            )
        )
        val imageFile = DisplayImageFile(ByteArray(10))
        val tags = Tags(listOf(Tag(TagId(2), TagName("testTagName2"))))

        val expected = IndexDataList(
            listOf(IndexData(DictionaryId(2), Name("testName2"), Description("testDescription2"), imageFile, tags))
        )

        every { indexDataListPort.searchByKeywords(any()) } returns searchedByKeywords
        every { indexDataListPort.searchByTags(any()) } returns searchedByTags
        every { storagePort.getImageFile(any()) } returns imageFile
        every { tagsPort.getSelectedTags(any()) } returns tags

        val actual = indexDataListUseCase.searchByKeywords(keywordSearchCondition, tagSearchCondition)
        assertThat(expected, `is`(actual))

        verify { indexDataListPort.searchByKeywords(keywordSearchCondition) }
        verify { indexDataListPort.searchByTags(tagSearchCondition) }
        verify { storagePort.getImageFile(any()) }
        verify { tagsPort.getSelectedTags(DictionaryId(2)) }
    }

    @Test
    fun deleteIndexTest() {
        val dictionaryId = DictionaryId(1)

        every { indexDataListPort.deleteIndex(any()) } just runs
        every { storagePort.deleteImage(any()) } just runs

        indexDataListUseCase.deleteIndex(dictionaryId)

        verify { indexDataListPort.deleteIndex(dictionaryId) }
        verify { storagePort.deleteImage(dictionaryId) }
    }

    @Test
    fun updateDictionaryTest() {
        val dictionaryId = DictionaryId(1)
        val name = Name("name")
        val description = Description("description")
        val imageFile = ImageFile(mockk())
        val tags = Tags(
            listOf(
                Tag(TagId(0), TagName("newTag")),
                Tag(TagId(0), TagName("newTag2")),
                Tag(TagId(1), TagName("registeredTag"))
            )
        )

        every { indexDataListPort.updateDictionary(any(), any(), any()) } just runs
        every { storagePort.updateImage(any(), any()) } just runs
        every { tagsPort.insertTag(TagName("newTag")) } returns 2
        every { tagsPort.insertTag(TagName("newTag2")) } returns 3
        every { tagsPort.updateTags(any(), any()) } just runs

        indexDataListUseCase.updateDictionary(dictionaryId, name, description, imageFile, tags)

        verify { indexDataListPort.updateDictionary(dictionaryId, name, description) }
        verify { storagePort.updateImage(dictionaryId, imageFile) }
        verify { tagsPort.insertTag(TagName("newTag")) }
        verify { tagsPort.insertTag(TagName("newTag2")) }
        verify { tagsPort.updateTags(dictionaryId, TagIds(listOf(TagId(2), TagId(3), TagId(1)))) }
    }
}