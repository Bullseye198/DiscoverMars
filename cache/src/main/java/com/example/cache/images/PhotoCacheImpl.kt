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

    override suspend fun requestImages(earthDate: String?, camera: String?): List<Image> {
        val roomImages = if (earthDate == null && camera == null) {
            imageDao.getImages()
        } else if (camera == null && earthDate != null) {
            imageDao.getImagesForDate(earthDate)
        } else if (earthDate != null && camera != null) {
            imageDao.getImagesForCameraAndDate(earthDate, camera)
        } else {
            imageDao.getImagesForCamera(camera!!)
        }

        return roomImages.map { databaseImage ->
            databaseImage.mapToDomainModel()
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