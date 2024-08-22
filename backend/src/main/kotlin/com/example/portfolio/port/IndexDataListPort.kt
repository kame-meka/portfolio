package com.example.portfolio.port

import com.example.portfolio.domain.*
import org.springframework.stereotype.Service

@Service
interface IndexDataListPort {
    fun getIndexDataList(): IndexDataList
    fun registerIndex(name: Name, description: Description, imageFile: ImageFile, tags: Tags)
    fun searchByKeywords(keywordSearchCondition: KeywordSearchCondition): IndexDataList?
    fun searchByTags(tagSearchCondition: TagSearchCondition): IndexDataList?
    fun deleteIndex(dictionaryId: DictionaryId)
    fun updateDictionary(dictionaryId: DictionaryId, name: Name, description: Description)
}
