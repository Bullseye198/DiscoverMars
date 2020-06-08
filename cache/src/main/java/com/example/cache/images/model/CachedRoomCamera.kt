package com.example.cache.images.model

import androidx.room.Entity
import com.example.domain.image.model.Camera

@Entity(
    tableName = "cameras",
    primaryKeys = ["fullName", "cameraImageId", "cameraRoverId"]
)
data class RoomCamera(
    val fullName: String,
    val cameraImageId: Int,
    val cameraRoverId: Int,
    val name: String
)

fun RoomCamera.mapToDomainModel(): Camera {
    return Camera(
        fullName = fullName,
        name = name,
        roverId = cameraRoverId
    )
}

fun Camera.mapToRoomModel(cameraImageId: Int?): RoomCamera {
    return RoomCamera(
        fullName = fullName,
        cameraImageId = cameraImageId ?: -1,
        name = name,
        cameraRoverId = roverId ?: -1
    )
}