package com.example.cache.images

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
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
    val camera: String
)

fun RoomImage.mapToDomainModel(): Image {
    return Image(
        creationDate = creationDate,
        contents = contents,
        imageUrl = imageUrl,
        id = id,
        camera = camera
    )
}

fun Image.mapToRoomModel(): RoomImage {
    return RoomImage(
        creationDate = creationDate,
        contents = contents,
        imageUrl = imageUrl,
        id = id,
        camera = camera
    )
}