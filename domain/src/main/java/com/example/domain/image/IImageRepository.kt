package com.example.domain.image

import com.example.domain.image.model.Image
import kotlinx.coroutines.flow.Flow


interface IImageRepository {

    fun observeImages(
        earthDate: String,
        rover: String
    ): Flow<List<Image>>

    //from Server - Remote
    suspend fun fetchImages(
        earthDate: String,
        rover: String
    ): List<Image>

    suspend fun storeImages(images: List<Image>)

    fun observeImage(id: Int): Flow<Image>
}