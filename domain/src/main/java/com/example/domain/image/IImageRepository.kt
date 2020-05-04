package com.example.domain.image

import com.example.domain.image.model.Image


interface IImageRepository {
    suspend fun getImageById(ImageId: Int): Image
    suspend fun getImages(): List<Image>
}