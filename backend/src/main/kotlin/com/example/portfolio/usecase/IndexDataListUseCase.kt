package com.example.portfolio.usecase

import com.example.portfolio.domain.*
import com.example.portfolio.port.IndexDataListPort
import com.example.portfolio.port.StoragePort
import com.example.portfolio.port.TagsPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IndexDataListUseCase {
    @Autowired
    lateinit var indexDataListPort: IndexDataListPort

    @Autowired
    lateinit var storagePort: StoragePort

    @Autowired
    lateinit var tagsPort: TagsPort

    fun getIndexDataList(): IndexDataList {
        val result = indexDataListPort.getIndexDataList()
        return result.list.map {
            val displayImageFile = storagePort.getImageFile(it.dictionaryId)
            val tags = tagsPort.getSelectedTags(it.dictionaryId)
            IndexData(
                it.dictionaryId,
                it.name,
                it.description,
                displayImageFile,
                tags
            )
        }.let(::IndexDataList)
    }

    fun registerIndex(name: Name, description: Description, imageFile: ImageFile, tags: Tags) {
        indexDataListPort.registerIndex(name, description, imageFile, tags)
    }

    fun searchByKeywords(keywordSearchCondition: KeywordSearchCondition?, tagSearchCondition: TagSearchCondition?): IndexDataList? {
        val keywordSearchResults = keywordSearchCondition?.let {
            indexDataListPort.searchByKeywords(keywordSearchCondition)
        }
        val tagSearchResults = tagSearchCondition?.let{
            indexDataListPort.searchByTags(tagSearchCondition)
        }
        val combinedIndexData = if(keywordSearchResults?.list.isNullOrEmpty() && tagSearchResults?.list.isNullOrEmpty()) {
            null
        } else if (keywordSearchResults?.list.isNullOrEmpty() && !tagSearchResults?.list.isNullOrEmpty()) {
            tagSearchResults!!.list
        } else if (!keywordSearchResults?.list.isNullOrEmpty() && tagSearchResults?.list.isNullOrEmpty()) {
            keywordSearchResults!!.list
        } else {
            (keywordSearchResults!!.list + tagSearchResults!!.list)
                .distinctBy { it.dictionaryId }
                .toList()
        }

        return combinedIndexData?.map {
            val displayImageFile = storagePort.getImageFile(it.dictionaryId)
            val tags = tagsPort.getSelectedTags(it.dictionaryId)
            IndexData(
                it.dictionaryId,
                it.name,
                it.description,
                displayImageFile,
                tags
            )
        }?.let(::IndexDataList)
    }

    fun deleteIndex(dictionaryId: DictionaryId) {
        indexDataListPort.deleteIndex(dictionaryId)
        storagePort.deleteImage(dictionaryId)
    }

    fun updateDictionary(
        dictionaryId: DictionaryId,
        name: Name,
        description: Description,
        imageFile: ImageFile?,
        tags: Tags
    ) {
        indexDataListPort.updateDictionary(dictionaryId, name, description)
        val tagIds = tags.list.map{
            if(it.tagId.value == 0) {
                val newTagId = tagsPort.insertTag(it.tagName)
                TagId(newTagId)
            } else it.tagId
        }.let(::TagIds)
        imageFile?.let {
            storagePort.updateImage(dictionaryId, imageFile)
        }
        tagsPort.updateTags(dictionaryId, tagIds)
    }

}
