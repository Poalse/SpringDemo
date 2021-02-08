package com.example.demo.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*

@Service
class StoreService {
    @Value("\${upload.dir}")
    private lateinit var uploadDir: String

    fun saveFile(uploadFile: MultipartFile): String {
        File(uploadDir).apply { if (!exists()) mkdir() }

        val file = File("${uploadDir}${File.separator}${uploadFile.originalFilename}-uuid${UUID.randomUUID()}")
        file.createNewFile()
        uploadFile.transferTo(file)
        return file.name
    }

    fun deleteFile(fileName: String) {
        File("${uploadDir}${File.separator}${fileName}").apply {
            println(absolutePath)
            delete()
        }
    }

    fun getFile(fileName: String) = File("${uploadDir}${File.separator}${fileName}")
}