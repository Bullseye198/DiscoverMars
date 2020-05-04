package com.example.cache

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ImageDao {
    @Query("SELECT * FROM mars")
    suspend fun getImages(): List<RoomImage>
}