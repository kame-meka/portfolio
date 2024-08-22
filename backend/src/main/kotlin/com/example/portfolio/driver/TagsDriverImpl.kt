package com.example.portfolio.driver

import com.example.ktknowledgeTodo.infra.jooq.tables.records.TagsRecord
import com.example.ktknowledgeTodo.infra.jooq.tables.references.TAGS
import com.example.ktknowledgeTodo.infra.jooq.tables.references.TAGS_RELATION_MAP
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class TagsDriverImpl(private val dslContext: DSLContext): TagsDriver {
    override fun findSelectedTagByIndexId(id: Int): List<TagsRecord>? {
        return dslContext
            .select()
            .from(TAGS)
            .join(TAGS_RELATION_MAP).on(TAGS_RELATION_MAP.TAG_ID.eq(TAGS.ID))
            .where(TAGS_RELATION_MAP.DICTIONARY_ID.eq(id))
            .fetchInto(TagsRecord::class.java)
    }

    override fun getAllTags(): List<TagsRecord>? {
        return dslContext
            .select()
            .from(TAGS)
            .fetchInto(TagsRecord::class.java)
    }

    override fun insertTagRelationMap(indexId: Int, tagIds: List<Int>) {
        tagIds.forEach { tagId ->
            dslContext
                .insertInto(TAGS_RELATION_MAP)
                .set(TAGS_RELATION_MAP.DICTIONARY_ID, indexId)
                .set(TAGS_RELATION_MAP.TAG_ID, tagId)
                .set(TAGS_RELATION_MAP.CREATED_AT, LocalDateTime.now())
                .execute()
        }
    }

    override fun deleteTagsRelationMap(dictionaryId: Int) {
        dslContext
            .deleteFrom(TAGS_RELATION_MAP)
            .where(TAGS_RELATION_MAP.DICTIONARY_ID.eq(dictionaryId))
            .execute()
    }

    override fun insertTag(tagName: String): Int {
        val record = dslContext.insertInto(TAGS)
            .set(TAGS.NAME, tagName)
            .set(TAGS.CREATED_AT, LocalDateTime.now())
            .returning(TAGS.ID)
            .fetchOne()
        return record?.id ?: 0
    }
}