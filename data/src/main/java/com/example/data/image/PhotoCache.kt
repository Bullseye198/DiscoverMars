package com.example.data.image

import com.example.domain.image.model.Image
import io.reactivex.Flowable


interface PhotoCache {
    suspend fun requestImages(earthDate: String?, camera: String?, rover: String?): List<Image>

    fun observeImages(): Flowable<List<Image>>

    fun observeImage(id: String): Flowable<Image>

    suspend fun storeImages(images: List<Image>)
}