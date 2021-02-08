package com.example.demo.dto.model

import java.time.LocalDate

data class TaskDto(
    val id: Long?,
    val name: String,
    val description: String?,
    val date: LocalDate,
    val tagId: Long
)