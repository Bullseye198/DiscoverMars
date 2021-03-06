package com.example.cache.images

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.images.dao.CamerasDao
import com.example.cache.images.dao.ImageDao
import com.example.cache.images.dao.RoverDao
import com.example.cache.images.model.RoomCamera
import com.example.cache.images.model.RoomImage
import com.example.cache.images.model.RoomRover

private const val DATABASE = "mars"

@Database(
    entities = [RoomImage::class, RoomRover::class, RoomCamera::class],
    version = 1,
    exportSchema = false
)

abstract class RoomImageDatabase : RoomDatabase() {
    abstract fun roomImageDao(): ImageDao
    abstract fun roomCamerasDao(): CamerasDao
    abstract fun roomRoverDao(): RoverDao
}
