package com.example.domain.image

import com.example.domain.image.model.Image
import io.reactivex.Flowable


interface IImageRepository {
    suspend fun getImageById(ImageId: Int): Image

    fun observeImages(): Flowable<List<Image>>

    //One Time Request from local Database
    suspend fun requestImages(earthDate: String?, camera: String?, rover: String?): List<Image>

    //from Server - Remote
    suspend fun fetchImages(
        earthDate: String = "",
        camera: String = "",
        rover: String = ""
    ): List<Image>

    suspend fun storeImages(images: List<Image>)
}