package com.example.domain.image.model

data class Image (
    val creationDate: String,
    val contents: String,
    val imageUrl: String,
    val id: Int,
    val camera: String
)