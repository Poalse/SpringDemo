package com.example.demo.controllers

import com.example.demo.dto.model.TaskDto
import com.example.demo.services.TaskService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream


@RestController
class TaskController(
    private val taskService: TaskService
) {
    @GetMapping("tasks")
    fun tasks(): List<TaskDto> = taskService.getAll()

    @GetMapping("tag/{tagId}")
    fun tasksByTag(@PathVariable tagId: Long) = taskService.tasksByTagId(tagId)

    @PostMapping("task")
    fun saveTask(@RequestParam taskDto: String, @RequestParam file: MultipartFile? = null): TaskDto {
        val mapper = ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())

        return taskService.save(mapper.readValue(taskDto), file)
    }

    @DeleteMapping("task/{taskId}")
    fun delete(@PathVariable taskId: Long) = taskService.deleteById(taskId)

    @GetMapping("task/{taskId}/file")
    fun downloadFile(@PathVariable taskId: Long): ResponseEntity<Resource> {
        val file = taskService.downloadFileFromTask(taskId)
        val resource = InputStreamResource(FileInputStream(file))

        val downloadFileName = "-uuid(.+)".toRegex().replace(file.name, "")

        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${downloadFileName}")

        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource)
    }
}