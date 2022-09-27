package com.jherrera.domain.entities

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)