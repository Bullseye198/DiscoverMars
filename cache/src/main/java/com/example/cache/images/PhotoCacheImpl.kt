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

    override fun observeImages(earthDate: String, rover: String): Flowable<List<Image>> {
        return imageDao.observeImages(earthDate, rover)
            .map { roomImages ->
                roomImages.map { it.mapToDomainModel() }
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
        val allCamerasForRover = images   // list of domain image
            .mapNotNull { it.rover } // list of domain rover
            .map { domainRover ->
                domainRover.cameras?.map {
                    RoomCamera(
                        fullName = it.fullName,
                        cameraRoverId = domainRover.id,
                        cameraImageId = -1,
                        name = it.name
                    )
                }
            } //list of list of roomCameras
            .reduce { acc, list -> acc } // list of roomCameras

        val allCamerasForImage =
            images.mapNotNull { domainImages -> domainImages.camera?.mapToRoomModel(domainImages.id) }

        roverDao.insertRover(allRovers)
        if (allCamerasForRover != null) {
            camerasDao.insertCameras(allCamerasForRover)
        }
        camerasDao.insertCameras(allCamerasForImage)
    }

    override fun observeImage(id: Int): Flowable<Image> {
        return imageDao.observeImage(id)
            .map { it.mapToDomainModel() }
    }
}