package com.example.demo.dto.model

data class TasksWithTagDto(
    val tag: TagDto,
    val tasks: List<TaskDto>
)