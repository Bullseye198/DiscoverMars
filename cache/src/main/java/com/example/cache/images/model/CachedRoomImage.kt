package com.example.cache.images.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.image.model.Image


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
    val sol: Int
)

fun RoomImage.mapToDomainModelList(rover: RoverAndCameras?, camera: RoomCamera?): Image {
    return Image(
        creationDate = creationDate,
        contents = contents,
        imageUrl = imageUrl,
        id = id,
        camera = camera?.mapToDomainModel(),
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
        sol = sol
    )
}