package com.example.cache.images

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.domain.image.model.Image


@Entity(
    tableName = "mars",
    indices = [Index("creation_date")]
)
data class RoomImage (
    @PrimaryKey
    @ColumnInfo(name = "creation_date")
    val creationDate: String
)

fun RoomImage.mapToDomainModel(): Image {
    return   Image(
        creationDate = creationDate,
        contents = "Test",
        imageUrl = "Test",
        id = creationDate.hashCode()
    )
}

fun Image.mapToRoomModel(): RoomImage {
    return RoomImage(
        creationDate = creationDate
    )
}