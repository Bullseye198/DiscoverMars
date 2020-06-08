package com.example.cache.images.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.domain.image.model.Image

data class ImageAndRover(
    @Embedded
    var roomImage: RoomImage,
    @Relation(parentColumn = "id", entityColumn = "roverImageId", entity = RoomRover::class)
    var roomRover: RoverAndCameras?,
    @Relation(parentColumn = "id", entityColumn = "cameraImageId")
    var roomCamera: RoomCamera?
)

fun ImageAndRover.mapToDomainModel(): Image {
    return roomImage.mapToDomainModelList(roomRover, roomCamera)
}