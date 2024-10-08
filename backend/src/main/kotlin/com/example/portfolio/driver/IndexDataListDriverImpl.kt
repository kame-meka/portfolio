package com.example.portfolio.driver

import com.example.ktknowledgeTodo.infra.jooq.tables.Tags.Companion.TAGS
import com.example.ktknowledgeTodo.infra.jooq.tables.records.DictionaryRecord
import com.example.ktknowledgeTodo.infra.jooq.tables.references.DICTIONARY
import com.example.ktknowledgeTodo.infra.jooq.tables.references.TAGS_RELATION_MAP
import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.stereotype.Repository
import java.lang.reflect.InvocationTargetException
import java.time.LocalDateTime
import kotlin.Exception

@Repository
class IndexDataListDriverImpl(private val dslContext: DSLContext): IndexDataListDriver {
    override fun findIndexDataList(): List<DictionaryRecord> {
        return this.dslContext
            .select()
            .from(DICTIONARY)
            .fetchInto(DictionaryRecord::class.java)
    }

    override fun insertIndexDataList(name: String, description: String): Int {
        try {
            val record = dslContext.insertInto(DICTIONARY)
                .set(DICTIONARY.NAME, name)
                .set(DICTIONARY.DESCRIPTION, description)
                .set(DICTIONARY.CREATED_AT, LocalDateTime.now())
                .returning(DICTIONARY.ID)
                .fetchOne()
            return record?.getValue(DICTIONARY.ID) ?: throw Exception("failed to insert")
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
            e.cause?.printStackTrace()
            return 1
        } catch (e: Exception) {
            e.printStackTrace()
            e.cause?.printStackTrace()
            return 1
        }
    }

    override fun searchByKeywords(keywords: List<String>, orCondition: Boolean): List<DictionaryRecord> {
        return if(orCondition) {
            // OR
            val conditions = keywords.map { keyword ->
                concat(field("name"), inline(" "), field("description")).like("%$keyword%")
            }
            this.dslContext
                .select()
                .from(DICTIONARY)
                .where(or(*conditions.toTypedArray()))
                .fetchInto(DictionaryRecord::class.java)
        } else {
            // AND
            val conditions = keywords.map { keyword ->
                concat(field("name"), inline(" "), field("description")).like("%$keyword%")
            }
            this.dslContext
                .select()
                .from(DICTIONARY)
                .where(and(*conditions.toTypedArray()))
                .fetchInto(DictionaryRecord::class.java)
        }
    }

    override fun deleteById(i: Int) {
        this.dslContext
            .deleteFrom(DICTIONARY)
            .where(DICTIONARY.ID.eq(i))
            .execute()
    }

    override fun findByTagIds(tagIds: List<Int>, orCondition: Boolean): List<DictionaryRecord> {
        return if(orCondition) {
            // OR
            this.dslContext
                .select()
                .from(DICTIONARY)
                .join(TAGS_RELATION_MAP).on(TAGS_RELATION_MAP.DICTIONARY_ID.eq(DICTIONARY.ID))
                .join(TAGS).on(TAGS.ID.eq(TAGS_RELATION_MAP.TAG_ID))
                .where(TAGS.ID.`in`(tagIds))
                .fetchInto(DictionaryRecord::class.java)
        } else {
            // AND
            val conditions = tagIds.map { tagId ->
                TAGS.ID.eq(tagId)
            }
            this.dslContext
                .select()
                .from(DICTIONARY)
                .join(TAGS_RELATION_MAP).on(TAGS_RELATION_MAP.DICTIONARY_ID.eq(DICTIONARY.ID))
                .join(TAGS).on(TAGS.ID.eq(TAGS_RELATION_MAP.TAG_ID))
                .where(and(*conditions.toTypedArray()))
                .fetchInto(DictionaryRecord::class.java)
        }
    }

    override fun updateDictionary(descriptionId: Int, name: String, description: String) {
        dslContext
            .update(DICTIONARY)
            .set(DICTIONARY.NAME, name)
            .set(DICTIONARY.DESCRIPTION, description)
            .where(DICTIONARY.ID.eq(descriptionId))
            .execute()
    }
}
