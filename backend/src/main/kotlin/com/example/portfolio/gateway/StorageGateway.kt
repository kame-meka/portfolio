package com.example.portfolio.gateway

import com.example.portfolio.domain.*
import com.example.portfolio.driver.StorageDriver
import com.example.portfolio.port.StoragePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StorageGateway: StoragePort {
    @Autowired
    lateinit var storageDriver: StorageDriver

    override fun getImageFile(dictionaryId: DictionaryId): DisplayImageFile {
        return DisplayImageFile(storageDriver.getImageFileByName(dictionaryId.value.toString()))
    }

    override fun deleteImage(dictionaryId: DictionaryId) {
        storageDriver.deleteById(dictionaryId.value)
    }

    override fun updateImage(dictionaryId: DictionaryId, imageFile: ImageFile) {
        storageDriver.deleteById(dictionaryId.value)
        storageDriver.storeImage(imageFile.value, dictionaryId.value)
    }
}
