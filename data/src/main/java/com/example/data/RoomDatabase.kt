package com.example.data

import androidx.room.Database

private const val DATABASE = "mars"

@Database(
    entities = [RoomMars::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
        abstract fun roomMarsDao(): MarsDao

    companion object {

    }
}
