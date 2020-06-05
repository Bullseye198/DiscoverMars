package com.example.remote.photo

import com.example.data.image.PhotoRemote
import com.example.domain.image.model.Image
import com.example.domain.image.model.Rover
import com.example.remote.photo.model.mapToDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRemoteImpl @Inject constructor(
    private val photoService: PhotoService
) : PhotoRemote {

    override suspend fun fetchImages(
        earthDate: String,
        camera: String,
        rover: String
    ): List<Image> {
        return photoService.getCurrentPhoto(
            rover = rover,
            earthDate = earthDate,
            key = "aiYJqOieQM1jp0oSvmuj1cEF6N8a4rY8tqtwy0HC",
            camera = if (camera.isNotEmpty()) {
                camera
            } else null
        ).photos
            .map {
                Image(it.earthDate, it.rover.name, it.imgSrc, it.id, it.camera.name, it.rover.mapToDomain(), it.sol)
            }
    }
}