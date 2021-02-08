package com.example.demo.dao.entity

import javax.persistence.*

@Entity
data class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagid")
    var id: Long? = null,

    val title: String
)
