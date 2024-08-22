package com.example.portfolio.usecase

import com.example.portfolio.domain.*
import com.example.portfolio.port.IndexDataListPort
import com.example.portfolio.port.StoragePort
import com.example.portfolio.port.TagsPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagsUseCase {
    @Autowired
    lateinit var tagsPort: TagsPort

    fun getAllTags(): Tags {
        return tagsPort.getAllTags()
    }
}
