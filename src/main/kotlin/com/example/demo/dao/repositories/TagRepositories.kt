package com.example.demo.dao.repositories

import com.example.demo.dao.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepositories : JpaRepository<Tag, Long> {
}