package com.example.domain.image

import com.example.domain.image.model.Image
import io.reactivex.Flowable


interface IImageRepository {
    suspend fun getImageById(ImageId: Int): Image

    //fun observeImages(): Flowable<List<Image>>

    //One Time Request from local Database
    suspend fun requestImages(): List<Image>

    //from Server - Remote
    suspend fun fetchImages(): List<Image>

    suspend fun storeImages(images: List<Image>)
}