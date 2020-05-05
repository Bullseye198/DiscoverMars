package com.example.data.image

import com.example.domain.image.model.Image
import io.reactivex.Flowable


interface PhotoCache {
    suspend fun requestImages(): List<Image>

    suspend fun observeImages(): Flowable<List<Image>>

    suspend fun storeImages(images: List<Image>)
}