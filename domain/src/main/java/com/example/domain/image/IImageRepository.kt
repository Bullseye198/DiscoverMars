package com.example.domain.image

import com.example.domain.image.model.Image
import com.example.domain.image.model.Rover
import io.reactivex.Flowable


interface IImageRepository {

    fun observeImages(earthDate: String, rover: String): Flowable<List<Image>>

    //from Server - Remote
    suspend fun fetchImages(
        earthDate: String,
        rover: String
    ): List<Image>

    suspend fun storeImages(images: List<Image>)

    fun observeImage(id: Int): Flowable<Image>
}