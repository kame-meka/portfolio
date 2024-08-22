package com.example.portfolio.domain

data class TagSearchCondition(val tagIds: TagIds, val orCondition: OrCondition)
data class Tags(val list: List<Tag>)
data class Tag(val tagId: TagId, val tagName: TagName)
data class TagId(val value: Int)
data class TagIds(val list: List<TagId>)
data class TagName(val value: String)
