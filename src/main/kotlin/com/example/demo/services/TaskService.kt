package com.example.demo.services

import com.example.demo.dao.entity.Task
import com.example.demo.dao.repositories.TagRepositories
import com.example.demo.dao.repositories.TaskRepositories
import com.example.demo.dto.mappers.TagMapper
import com.example.demo.dto.mappers.TaskMapper
import com.example.demo.dto.model.TaskDto
import com.example.demo.dto.model.TasksWithTagDto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class TaskService(
    private val taskRepositories: TaskRepositories,
    private val tagRepositories: TagRepositories,
    private val storeService: StoreService
) {
    fun getAll(): List<TaskDto> = taskRepositories.findAll().map(TaskMapper::toDto)

    fun tasksByTagId(tagId: Long): TasksWithTagDto {
        val tag = tagRepositories.getOne(tagId)
        val tasks = taskRepositories.getByTagId(tagId)
        return TasksWithTagDto(
            tag = TagMapper.toDto(tag),
            tasks = tasks.map(TaskMapper::toDto)
        )
    }

    fun save(taskDto: TaskDto, file: MultipartFile?): TaskDto {
        if (taskRepositories.existsById(taskDto.tagId) && file != null) {
            val task = taskRepositories.getOne(taskDto.tagId)
            if (task.file != null) storeService.deleteFile(task.file)
        }

        val fileName = file?.let { storeService.saveFile(it) }
        return TaskMapper.toDto(
            taskRepositories.save(
                Task(
                    id = taskDto.id,
                    name = taskDto.name,
                    description = taskDto.description,
                    file = fileName,
                    date = taskDto.date,
                    tagId = taskDto.tagId
                )
            )
        )
    }

    fun deleteById(id: Long) {
        taskRepositories.getOne(id).file.apply { this?.let { storeService.deleteFile(it) } }
        taskRepositories.deleteById(id)
    }

    fun downloadFileFromTask(id: Long): File {
        return storeService.getFile(
            fileName = taskRepositories.getOne(id).file ?: error("File not uploaded!")
        )
    }
}