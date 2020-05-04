package com.example.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE = "mars"

@Database(
    entities = [RoomImage::class],
    version = 1,
    exportSchema = false
)

abstract class RoomImageDatabase : RoomDatabase() {
        abstract fun roomImageDao(): ImageDao

    companion object {

        @Volatile
        private var instance: RoomImageDatabase? = null

        fun getInstance(context: Context): RoomImageDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RoomImageDatabase {
            return Room.databaseBuilder(context, RoomImageDatabase::class.java,
            DATABASE)
                .build()
        }
    }

}
