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
        return requestImages()
            .first { it.id == ImageId }
    }

    override suspend fun requestImages(): List<Image> {
        return photoCache.requestImages()
    }

    override suspend fun fetchImages(): List<Image> {
        return photoRemote.fetchImages()
    }

    override suspend fun storeImages(images: List<Image>) {
        photoCache.storeImages(images)
    }


}