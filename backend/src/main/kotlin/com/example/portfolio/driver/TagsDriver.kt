package com.example.portfolio.driver

import com.example.ktknowledgeTodo.infra.jooq.tables.records.TagsRecord
import org.springframework.stereotype.Repository

@Repository
interface TagsDriver {
    fun findSelectedTagByIndexId(id: Int): List<TagsRecord>?
    fun getAllTags(): List<TagsRecord>?
    fun insertTagRelationMap(indexId: Int, tagIds: List<Int>)
    fun deleteTagsRelationMap(dictionaryId: Int)
    fun insertTag(tagName: String): Int
}
