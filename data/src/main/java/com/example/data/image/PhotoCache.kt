package com.example.data.image

import com.example.domain.image.model.Image
import io.reactivex.Flowable


interface PhotoCache {

    fun observeImages(earthDate: String, rover: String): Flowable<List<Image>>

    fun observeImage(id: Int): Flowable<Image>

    suspend fun storeImages(images: List<Image>)
}