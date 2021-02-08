package com.example.demo.dao.repositories

import com.example.demo.dao.entity.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepositories : JpaRepository<Task, Long> {
    fun getByTagId(id: Long): List<Task>
}