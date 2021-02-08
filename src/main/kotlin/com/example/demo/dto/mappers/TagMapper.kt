package com.example.demo.dto.mappers

import com.example.demo.dao.entity.Tag
import com.example.demo.dto.model.TagDto

object TagMapper {
    fun toDto(tag: Tag): TagDto =
        TagDto(
            id = tag.id,
            title = tag.title
        )
}