package com.example.portfolio.driver

import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile

@Repository
interface StorageDriver {
    fun storeImage(imageFile: MultipartFile, insertId: Int)
    fun getImageFileByName(fileName: String): ByteArray
    fun deleteById(id: Int)
}
