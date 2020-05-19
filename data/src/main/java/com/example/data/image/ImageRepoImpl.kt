package com.example.data.image

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepoImpl @Inject constructor(
    private val photoRemote: PhotoRemote,
    private val photoCache: PhotoCache
) : IImageRepository {

    override suspend fun getImageById(ImageId: Int): Image {
        return requestImages(null, null, null)
            .first { it.id == ImageId }
    }

    override suspend fun requestImages(
        earthDate: String?,
        camera: String?,
        rover: String?
    ): List<Image> {
        return photoCache.requestImages(earthDate, camera, rover)
    }

    override suspend fun fetchImages(
        earthDate: String,
        camera: String,
        rover: String
    ): List<Image> {
        return photoRemote.fetchImages(earthDate, camera, rover)
    }

    override suspend fun storeImages(images: List<Image>) {
        photoCache.storeImages(images)
    }


    override fun observeImages(): Flowable<List<Image>> {
        return photoCache.observeImages()
    }


}