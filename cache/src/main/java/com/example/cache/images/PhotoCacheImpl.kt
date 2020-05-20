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

    override suspend fun requestImages(
        earthDate: String?,
        camera: String?,
        rover: String?
    ): List<Image> {
        val roomImages = if (earthDate == null && camera == null) {
            imageDao.getImages()
        } else if (camera == null && earthDate != null && rover != null) {
            imageDao.getImagesForDate(earthDate, rover)
        } else if (earthDate != null && camera != null && rover != null) {
            imageDao.getImagesForCameraAndDate(earthDate, camera, rover)
        } else {
            imageDao.getImagesForCamera(camera!!, rover!!)
        }

        return roomImages.map { databaseImage ->
            databaseImage.mapToDomainModel()
        }
    }

    override fun observeImages(): Flowable<List<Image>> {
        return imageDao.observeImages()
            .map { roomImages ->
                roomImages.map { it.mapToDomainModel() }
            }
    }

    override suspend fun storeImages(images: List<Image>) {
        imageDao.insertAllSuspend(images.map { domainImage ->
            domainImage.mapToRoomModel()
        })
    }
}