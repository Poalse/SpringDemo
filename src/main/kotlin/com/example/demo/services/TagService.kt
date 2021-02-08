package com.example.demo.services

import com.example.demo.dao.entity.Tag
import com.example.demo.dao.repositories.TagRepositories
import com.example.demo.dto.mappers.TagMapper
import com.example.demo.dto.model.TagDto
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepositories: TagRepositories
) {
    fun getAll(): List<TagDto> = tagRepositories.findAll().map(TagMapper::toDto)

    fun save(tagDto: TagDto) = TagMapper.toDto(
        tagRepositories.save(
            Tag(
                id = tagDto.id,
                title = tagDto.title
            )
        )
    )

    fun deleteById(id: Long) = tagRepositories.deleteById(id)
}