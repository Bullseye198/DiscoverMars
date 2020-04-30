package com.example.domain.image

import com.example.domain.image.model.Image


interface IImageRepository {

    suspend fun getImages(): List<Image>
}