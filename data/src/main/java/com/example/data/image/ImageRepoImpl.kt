package com.example.data.image

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepoImpl @Inject constructor(
    private val photoRemote: PhotoRemote,
    private val photoCache: PhotoCache
) : IImageRepository{

    override suspend fun getImageById(ImageId: Int): Image {
        return requestImages(null)
            .first { it.id == ImageId }
    }

    override suspend fun requestImages(camera: String?): List<Image> {
        return photoCache.requestImages(camera)
    }

    override suspend fun fetchImages(camera: String): List<Image> {
        return photoRemote.fetchImages(camera)
    }

    override suspend fun storeImages(images: List<Image>) {
        photoCache.storeImages(images)
    }


}