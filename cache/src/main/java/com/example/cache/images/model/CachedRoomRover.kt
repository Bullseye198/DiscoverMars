package com.example.cache.images.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.image.model.CameraX
import com.example.domain.image.model.Rover

@Entity(
    tableName = "rover",
    primaryKeys = ["id", "roverImageId"]
)
data class RoomRover(
    val id: Int,
    val roverImageId: Int,
    val landingDate: String,
    val launchDate: String,
    val maxDate: String,
    val maxSol: Int,
    val name: String,
    val status: String,
    val totalPhotos: Int
)

fun RoomRover.mapToDomainRover(cameras: List<RoomCameras>): Rover {
    return Rover(
        id = id,
        landingDate = landingDate,
        launchDate = launchDate,
        maxDate = maxDate,
        maxSol = maxSol,
        name = name,
        status = status,
        totalPhotos = totalPhotos,
        cameras = cameras.map { CameraX(it.fullName, it.name) }
    )
}

fun Rover.mapToRoomRoverModel(roverImageId: Int): RoomRover {
    return RoomRover(
        id = id,
        landingDate = landingDate,
        launchDate = launchDate,
        maxDate = maxDate,
        maxSol = maxSol,
        name = name,
        status = status,
        totalPhotos = totalPhotos,
        roverImageId = roverImageId
    )
}