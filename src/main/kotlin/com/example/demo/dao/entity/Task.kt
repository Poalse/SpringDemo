package com.example.demo.dao.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskid")
    val id: Long? = null,

    val name: String,

    val description: String? = null,

    val date: LocalDate,

    val file: String? = null,

    @JoinColumn(name = "tagid")
    val tagId: Long
)
