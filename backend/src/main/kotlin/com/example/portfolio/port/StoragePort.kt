package com.example.portfolio.port

import com.example.portfolio.domain.*
import org.springframework.stereotype.Service

@Service
interface StoragePort {
    fun getImageFile(dictionaryId: DictionaryId): DisplayImageFile?
    fun deleteImage(dictionaryId: DictionaryId)
    fun updateImage(dictionaryId: DictionaryId, imageFile: ImageFile)
}
