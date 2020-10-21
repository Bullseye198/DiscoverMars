package com.example.data.image

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import kotlinx.coroutines.flow.Flow
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

    override fun observeImage(id: Int): Flow<Image> {
        return photoCache.observeImage(id)
    }

    override fun observeImages(earthDate: String, rover: String): Flow<List<Image>> {
        return photoCache.observeImages(earthDate, rover)
    }
}