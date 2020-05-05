package com.example.cache.images

import com.example.cache.images.dao.ImageDao
import com.example.data.image.PhotoCache
import com.example.domain.image.model.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoCacheImpl @Inject constructor(
    private val imageDao: ImageDao
) : PhotoCache {

    override suspend fun getImagesSuspend(): List<Image> {
        return imageDao.getImages()
            .map { databaseImage ->
                databaseImage.mapToDomainModel()
            }

    }

    override suspend fun storeImages(images: List<Image>) {
        imageDao.insertAllSuspend(images.map { domainImage ->
            domainImage.mapToRoomModel()
        })
    }
}