package com.example.cache.images

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.images.dao.ImageDao

private const val DATABASE = "mars"

@Database(
    entities = [RoomImage::class],
    version = 2,
    exportSchema = false
)

abstract class RoomImageDatabase : RoomDatabase() {
    abstract fun roomImageDao(): ImageDao
}
