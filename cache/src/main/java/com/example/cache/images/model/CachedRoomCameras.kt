package com.example.cache.images.model

import androidx.room.Entity

@Entity(
    tableName = "cameras",
    primaryKeys = ["fullName", "cameraImageId"]
)
data class RoomCameras(
    val fullName: String,
    val cameraImageId: Int,
    val name: String
)