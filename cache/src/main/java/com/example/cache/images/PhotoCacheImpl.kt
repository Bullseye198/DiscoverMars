package com.example.cache.images

import com.example.cache.images.dao.CamerasDao
import com.example.cache.images.dao.ImageDao
import com.example.cache.images.dao.RoverDao
import com.example.cache.images.model.*
import com.example.data.image.PhotoCache
import com.example.domain.image.model.Image
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoCacheImpl @Inject constructor(
    private val imageDao: ImageDao,
    private val roverDao: RoverDao,
    private val camerasDao: CamerasDao
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
        //List of Domain Images
        //We want to store room images
        imageDao.insertAllSuspend(images.map { domainImage ->
            domainImage.mapToRoomModel()
        })

        //we didn't store rovers and cameras
        //store all rovers
        val allRovers = images
            .mapNotNull { domainImage -> domainImage.rover?.mapToRoomRoverModel(domainImage.id) }

        //after map we have List<List<Camera>> what we want is List<Camera>
        val allCameras = images   // list of domain image
            .mapNotNull { it.rover } // list of domain rover
            .map { domainRover ->
                domainRover.cameras.map {
                    RoomCameras(
                        it.fullName,
                        domainRover.id,
                        it.name
                    )
                }
            } //list of list of roomCameras
            .reduce { acc, list -> acc + list } // list of roomCameras

        roverDao.insertRover(allRovers)
        camerasDao.insertCameras(allCameras)
    }

    override fun observeImage(id: Int): Flowable<Image> {
        return imageDao.observeImage(id)
            .map { it.mapToDomainModel() }
    }
}