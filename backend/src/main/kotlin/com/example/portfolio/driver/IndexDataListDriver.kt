package com.example.portfolio.driver

import com.example.ktknowledgeTodo.infra.jooq.tables.records.DictionaryRecord
import org.springframework.stereotype.Repository

@Repository
interface IndexDataListDriver {
    fun findIndexDataList(): List<DictionaryRecord>
    fun insertIndexDataList(name: String, description: String): Int
    fun searchByKeywords(keywords: List<String>, orCondition: Boolean): List<DictionaryRecord>
    fun deleteById(i: Int)
    fun findByTagIds(tagIds: List<Int>, orCondition: Boolean): List<DictionaryRecord>
    fun updateDictionary(descriptionId: Int, name: String, description: String)
}
