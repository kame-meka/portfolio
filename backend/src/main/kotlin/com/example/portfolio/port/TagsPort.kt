package com.example.portfolio.port

import com.example.portfolio.domain.*
import org.springframework.stereotype.Service

@Service
interface TagsPort {
    fun getSelectedTags(dictionaryId: DictionaryId): Tags
    fun getAllTags(): Tags
    fun updateTags(dictionaryId: DictionaryId, tagIds: TagIds)
    fun insertTag(tagName: TagName): Int
}
