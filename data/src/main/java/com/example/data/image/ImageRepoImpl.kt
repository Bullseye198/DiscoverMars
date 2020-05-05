package com.example.data.image

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepoImpl @Inject constructor(
    private val photoRemote: PhotoRemote,
    private val photoLocal: PhotoCache
) : IImageRepository{

    override suspend fun getImageById(ImageId: Int): Image {
        return photoRemote.getImages()
            .first { it.id == ImageId }
    }


    override suspend fun getImages(): List<Image> {
        return photoRemote.getImages()
    }

    override suspend fun getImagesSuspend(): List<Image> {
        return photoLocal.getImagesSuspend()
    }

}