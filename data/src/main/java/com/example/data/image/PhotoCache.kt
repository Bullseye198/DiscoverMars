package com.example.data.image

import com.example.domain.image.model.Image
import kotlinx.coroutines.flow.Flow


interface PhotoCache {

    fun observeImages(earthDate: String, rover: String): Flow<List<Image>>

    fun observeImage(id: Int): Flow<Image>

    suspend fun storeImages(images: List<Image>)
}