package com.example.cache.images

import com.example.cache.images.dao.ImageDao
import com.example.cache.images.model.mapToDomainModel
import com.example.cache.images.model.mapToDomainModelList
import com.example.cache.images.model.mapToRoomModel
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
            databaseImage.mapToDomainModelList(null)
        }
    }

    override fun observeImages(): Flowable<List<Image>> {
        return imageDao.observeImages()
            .map { roomImages ->
                roomImages.map { it.mapToDomainModelList(null) }
            }
    }

    override suspend fun storeImages(images: List<Image>) {
        imageDao.insertAllSuspend(images.map { domainImage ->
            domainImage.mapToRoomModel()
        })
    }

    override fun observeImage(id: String): Flowable<Image> {
    return imageDao.observeImage(id)
        .map { it.mapToDomainModel() }
    }
}