package com.example.cache.images.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.image.model.CameraX
import com.example.domain.image.model.Image
import com.example.domain.image.model.Rover


@Entity(
    tableName = "mars"
)
data class RoomImage(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val creationDate: String,
    val contents: String,
    val imageUrl: String,
    val camera: String,
    val sol: Int
)

fun RoomImage.mapToDomainModelList(rover: RoverAndCameras?): Image {
    return Image(
        creationDate = creationDate,
        contents = contents,
        imageUrl = imageUrl,
        id = id,
        camera = camera,
        sol = sol,
        rover = rover?.mapToDomainModel()
    )
}

fun Image.mapToRoomModel(): RoomImage {
    return RoomImage(
        creationDate = creationDate,
        contents = contents,
        imageUrl = imageUrl,
        id = id,
        camera = camera,
        sol = sol
    )
}