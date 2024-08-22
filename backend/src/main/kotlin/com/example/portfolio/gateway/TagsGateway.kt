package com.example.portfolio.gateway

import com.example.portfolio.domain.*
import com.example.portfolio.driver.TagsDriver
import com.example.portfolio.port.TagsPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagsGateway: TagsPort {
    @Autowired
    lateinit var tagsDriver: TagsDriver

    override fun getSelectedTags(dictionaryId: DictionaryId): Tags {
        return tagsDriver.findSelectedTagByIndexId(dictionaryId.value)?.map {
            Tag(TagId(it.id ?: 0), TagName(it.name ?: ""))
        }?.let(::Tags) ?: Tags(listOf(Tag(TagId(0), TagName(""))))
    }

    override fun getAllTags(): Tags {
        return tagsDriver.getAllTags()?.map {
            Tag(TagId(it.id ?: 0), TagName(it.name ?: ""))
        }?.let(::Tags) ?: Tags(listOf(Tag(TagId(0), TagName(""))))
    }

    override fun updateTags(dictionaryId: DictionaryId, tagIds: TagIds) {
        tagsDriver.deleteTagsRelationMap(dictionaryId.value)
        tagsDriver.insertTagRelationMap(dictionaryId.value, tagIds.list.map{ it.value })
    }

    override fun insertTag(tagName: TagName): Int {
        return tagsDriver.insertTag(tagName.value)
    }
}
