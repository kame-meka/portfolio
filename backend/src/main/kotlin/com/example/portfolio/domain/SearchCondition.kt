package com.example.portfolio.domain

data class SearchCondition(val keywordSearchCondition: KeywordSearchCondition)

data class KeywordSearchCondition(val keywords: Keywords, val orCondition: OrCondition)
data class Keywords(val list: List<Keyword>)
data class Keyword(val value: String)
data class OrCondition(val value: Boolean)

