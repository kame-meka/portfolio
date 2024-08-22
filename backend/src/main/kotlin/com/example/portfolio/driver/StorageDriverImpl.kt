package com.example.portfolio.driver

import io.minio.GetObjectArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.io.IOException
import kotlin.Exception

@Repository
class StorageDriverImpl(): StorageDriver {
    override fun storeImage(imageFile: MultipartFile, insertId: Int) {
        try {
            MinioClient
                .builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("ROOTNAME", "CHANGEME123")
                .build()
                .putObject(
                    PutObjectArgs.builder()
                        .bucket("test-bucket")
                        .`object`(insertId.toString())
                        .stream(imageFile.inputStream, imageFile.size, -1)
                        .contentType(imageFile.contentType)
                        .build()
                )
        } catch (e: IOException) {
            throw IOException("IO error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unknown error has occurred")
        }
    }

    override fun getImageFileByName(fileName: String): ByteArray {
        return try {
            val response = MinioClient
                .builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("ROOTNAME", "CHANGEME123")
                .build()
                .getObject(
                    GetObjectArgs
                        .builder()
                        .bucket("test-bucket")
                        .`object`(fileName)
                        .build()
                )

            val byteArrayOutputStream = ByteArrayOutputStream()
            response.transferTo(byteArrayOutputStream)
            byteArrayOutputStream.toByteArray()
        } catch (e: IOException) {
            throw IOException("IO error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unknown error has occurred")
        }
    }

    override fun deleteById(id: Int) {
        try {
            MinioClient
                .builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("ROOTNAME", "CHANGEME123")
                .build()
                .removeObject(
                    RemoveObjectArgs.builder()
                        .bucket("test-bucket")
                        .`object`(id.toString())
                        .build()
                )
        } catch (e: IOException) {
            throw IOException("IO error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unknown error has occurred")
        }
    }
}