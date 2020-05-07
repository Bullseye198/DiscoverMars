package com.example.cache.images

import com.example.cache.images.dao.ImageDao
import com.example.data.image.PhotoCache
import com.example.domain.image.model.Image
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoCacheImpl @Inject constructor(
    private val imageDao: ImageDao
) : PhotoCache {

    override suspend fun requestImages(camera: String?): List<Image> {
        return if(camera == null) {
            imageDao.getImages()
                .map { databaseImage ->
                    databaseImage.mapToDomainModel()
                }
        } else {
            imageDao.getImagesForCamera(camera)
                .map { databaseImage ->
                    databaseImage.mapToDomainModel()
                }
        }

    }

    override suspend fun observeImages(): Flowable<List<Image>> {
        return Flowable.empty()
    }

    override suspend fun storeImages(images: List<Image>) {
        imageDao.insertAllSuspend(images.map { domainImage ->
            domainImage.mapToRoomModel()
        })
    }
}