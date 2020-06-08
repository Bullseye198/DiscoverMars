package com.example.cache.images.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.domain.image.model.Rover

data class RoverAndCameras(
    @Embedded
    var roomRover: RoomRover,
    @Relation(parentColumn = "id", entityColumn = "roverImageId")
    var roomCameras: List<RoomCamera>
)

fun RoverAndCameras.mapToDomainModel(): Rover {
    return roomRover.mapToDomainRover(roomCameras)
}