package com.example.portfolio.domain

import org.springframework.web.multipart.MultipartFile

data class IndexDataList(val list: List<IndexData>)
data class IndexData(val dictionaryId :DictionaryId, val name: Name, val description: Description, val displayImageFile: DisplayImageFile?, val tags: Tags?)

data class DictionaryId(val value: Int)
data class Name(val value: String)
data class Description(val value: String)
data class ImageFile(val value: MultipartFile)
data class DisplayImageFile(val value: ByteArray)