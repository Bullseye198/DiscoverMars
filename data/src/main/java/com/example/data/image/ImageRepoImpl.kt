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

    override suspend fun fetchImages(
        earthDate: String,
        rover: String
    ): List<Image> {
        return photoRemote.fetchImages(earthDate, rover)
    }

    override suspend fun storeImages(images: List<Image>) {
        photoCache.storeImages(images)
    }

    override fun observeImage(id: Int): Flowable<Image> {
        return photoCache.observeImage(id)
    }

    override fun observeImages(earthDate: String, rover: String): Flowable<List<Image>> {
        return photoCache.observeImages(earthDate, rover)
    }
}