package com.example.portfolio.gateway

import com.example.portfolio.domain.*
import com.example.portfolio.driver.IndexDataListDriver
import com.example.portfolio.driver.StorageDriver
import com.example.portfolio.driver.TagsDriver
import com.example.portfolio.port.IndexDataListPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IndexDataListGateway: IndexDataListPort {
    @Autowired
    lateinit var indexDataListDriver: IndexDataListDriver

    @Autowired
    lateinit var storageDriver: StorageDriver

    @Autowired
    lateinit var tagsDriver: TagsDriver

    override fun getIndexDataList(): IndexDataList {
        val result = indexDataListDriver.findIndexDataList()
        return if (result.isEmpty()) {
            IndexDataList(listOf(IndexData(DictionaryId(0), Name(""), Description(""), null, null)))
        } else {
            IndexDataList(result.map { IndexData(DictionaryId(it.id ?: 0), Name(it.name ?: ""), Description(it.description ?: ""), null, null) })
        }
    }

    override fun registerIndex(name: Name, description: Description, imageFile: ImageFile, tags: Tags) {
        val insertId = indexDataListDriver.insertIndexDataList(name.value, description.value)
        val insertTags = tags.list.map{
            if(it.tagId.value == 0) {
                val newTagId = tagsDriver.insertTag(it.tagName.value)
                TagId(newTagId)
            } else it.tagId
        }
        tagsDriver.insertTagRelationMap(insertId, insertTags.map { it.value })
        storageDriver.storeImage(imageFile.value, insertId)
    }

    override fun searchByKeywords(keywordSearchCondition: KeywordSearchCondition): IndexDataList? {
        val result = indexDataListDriver.searchByKeywords(
            keywordSearchCondition.keywords.list.map { it.value },
            keywordSearchCondition.orCondition.value
        )
        return if (result.isEmpty()) {
            null
        } else {
            IndexDataList(result.map { IndexData(DictionaryId(it.id ?: 0), Name(it.name ?: ""), Description(it.description ?: ""), null, null) })
        }
    }

    override fun deleteIndex(dictionaryId: DictionaryId) {
        tagsDriver.deleteTagsRelationMap(dictionaryId.value)
        indexDataListDriver.deleteById(dictionaryId.value)
    }

    override fun updateDictionary(dictionaryId: DictionaryId, name: Name, description: Description) {
        indexDataListDriver.updateDictionary(dictionaryId.value, name.value, description.value)
    }

    override fun searchByTags(tagSearchCondition: TagSearchCondition): IndexDataList? {
        val result = indexDataListDriver.findByTagIds(
            tagSearchCondition.tagIds.list.map { it.value },
            tagSearchCondition.orCondition.value
        )
        return if (result.isEmpty()) {
            null
        } else {
            IndexDataList(result.map { IndexData(DictionaryId(it.id ?: 0), Name(it.name ?: ""), Description(it.description ?: ""), null, null) })
        }
    }

}
