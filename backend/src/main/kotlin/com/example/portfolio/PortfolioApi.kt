package com.example.portfolio

import com.example.portfolio.domain.*
import com.example.portfolio.usecase.IndexDataListUseCase
import com.example.portfolio.usecase.TagsUseCase
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class PortfolioApiController {

    @Autowired
    lateinit var indexDataListUseCase: IndexDataListUseCase

    @Autowired
    lateinit var tagsUseCase: TagsUseCase

    @GetMapping("/api/v1/load")
    fun getDefault(): ResponseEntity<IndexDataListJson> {
        val indexDataList = indexDataListUseCase.getIndexDataList()
        val json =  indexDataList.list.map {
            val tagsData = it.tags!!.list.map { tag -> TagJson(tag.tagId.value, tag.tagName.value) }.let(::TagsListJson)
            IndexDataJson(it.dictionaryId.value, it.name.value, it.description.value, it.displayImageFile!!.value, tagsData)
        }.let(::IndexDataListJson)
        return ResponseEntity(json, HttpStatus.OK)
    }

    @GetMapping("/api/v1/all-tags")
    fun getAllTags(): ResponseEntity<TagsListJson> {
        val tags = tagsUseCase.getAllTags()
        val json = tags.list.map{ TagJson(it.tagId.value, it.tagName.value) }.let(::TagsListJson)
        return ResponseEntity(json, HttpStatus.OK)
    }
    
    @GetMapping("/api/v1/search")
    fun searchIndex(
        @RequestParam("keywords") keywords: String,
        @RequestParam("keywordsOrCondition") keywordsOrCondition: Boolean,
        @RequestParam("tagIds") tagIds: List<Int>,
        @RequestParam("tagsOrCondition") tagsOrCondition: Boolean
    ): ResponseEntity<IndexDataListJson> {
        val keywordList = keywords.split("\\s|　".toRegex()).map { Keyword(it) }
        val tagIdsList = if (!tagIds.isEmpty()) {
            TagSearchCondition(tagIds.map { TagId(it) }.let(::TagIds), OrCondition(tagsOrCondition))
        } else null
        val keywordSearchCondition = if (keywords.isNotBlank()) {
            KeywordSearchCondition(Keywords(keywordList), OrCondition(keywordsOrCondition))
        } else null
        val indexDataList = indexDataListUseCase.searchByKeywords(keywordSearchCondition, tagIdsList)
        val json = indexDataList?.list?.map {
            val tagsData = it.tags!!.list.map { tag -> TagJson(tag.tagId.value, tag.tagName.value) }.let(::TagsListJson)
            IndexDataJson(it.dictionaryId.value, it.name.value, it.description.value, it.displayImageFile!!.value, tagsData)
        }?.let(::IndexDataListJson)
        return ResponseEntity(json, HttpStatus.OK)
    }

    @PostMapping("/api/v1/register")
    fun register(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("name") name: String,
        @RequestParam("description") description: String,
        @RequestParam("tagsData") tagsData: String
    ): ResponseEntity<String> {
        return try {
            val mapper = jacksonObjectMapper()
            val tagsDataObj: TagsListJson = mapper.readValue(tagsData)
            val tagList = tagsDataObj.list
                .map { Tag(TagId(it.tagId), TagName(it.tagName)) }
                .let(::Tags)
            indexDataListUseCase.registerIndex(Name(name), Description(description), ImageFile(file), tagList)
            ResponseEntity("Image uploaded successfully.", HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/api/v1/update")
    fun update(
        @RequestParam("id") id: Int,
        @RequestParam("name") name: String,
        @RequestParam("description") description: String,
        @RequestParam("file") file: MultipartFile?,
        @RequestParam("tagsData") tagsData: String
    ): ResponseEntity<String> {
        return try {
            val imageFile = file?.let{ ImageFile(file) }
            //val tagIdList = tagIds.map { TagId(it) }.let(::TagIds)
            val mapper = jacksonObjectMapper()
            val tagsDataObj: TagsListJson = mapper.readValue(tagsData)
            val tagList = tagsDataObj.list
                .map { Tag(TagId(it.tagId), TagName(it.tagName)) }
                .let(::Tags)
            indexDataListUseCase.updateDictionary(DictionaryId(id), Name(name), Description(description), imageFile, tagList)
            ResponseEntity("OK", HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/api/v1/delete/{id}")
    fun delete(
        @PathVariable id: Int
    ): ResponseEntity<String> {
        indexDataListUseCase.deleteIndex(DictionaryId(id))
        return ResponseEntity("", HttpStatus.OK)
    }

    data class IndexDataJson(val id: Int, val name: String, val description: String, val imageFile: ByteArray, val tags: TagsListJson)
    data class TagJson(val tagId: Int, val tagName: String)
    data class IndexDataListJson(val indexDataList: List<IndexDataJson>)
    data class TagsListJson(val list: List<TagJson>)
}