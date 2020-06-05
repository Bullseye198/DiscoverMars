package com.example.cache.images.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.domain.image.model.Rover

data class RoverAndCameras(
    @Embedded
    var roomRover: RoomRover,
    @Relation(parentColumn = "id", entityColumn = "cameraImageId")
    var roomCameras: List<RoomCameras>
)

fun RoverAndCameras.mapToDomainModel(): Rover {
    return roomRover.mapToDomainRover(roomCameras)
}