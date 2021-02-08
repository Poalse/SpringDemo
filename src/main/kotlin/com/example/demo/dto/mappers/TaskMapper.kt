package com.example.demo.dto.mappers

import com.example.demo.dao.entity.Task
import com.example.demo.dto.model.TaskDto

object TaskMapper {
    fun toDto(task: Task): TaskDto =
        TaskDto(
            id = task.id,
            name = task.name,
            description = task.description,
            date = task.date,
            tagId = task.tagId
        )
}