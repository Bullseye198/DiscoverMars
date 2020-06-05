package com.example.cache.images.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.domain.image.model.Image
import com.example.domain.image.model.Rover

data class ImageAndRover(
    @Embedded
    var roomImage: RoomImage,
    @Relation(parentColumn = "id", entityColumn = "roverImageId", entity = RoomRover::class)
    var roomRover: RoverAndCameras? = null
)

fun ImageAndRover.mapToDomainModel(): Image {
    return roomImage.mapToDomainModelList(roomRover)
}