package com.example.demo.controllers

import com.example.demo.dto.model.TagDto
import com.example.demo.services.TagService
import org.springframework.web.bind.annotation.*

@RestController
class TagController(
    private val tagService: TagService
) {
    @GetMapping("tags")
    fun tags(): List<TagDto> = tagService.getAll()

    @PostMapping("tag")
    fun saveTag(@RequestBody tagDto: TagDto): TagDto = tagService.save(tagDto)

    @DeleteMapping("tag/{tagId}")
    fun delete(@PathVariable tagId: Long) = tagService.deleteById(tagId)
}