package com.example.cache.images.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.cache.images.RoomImage

@Dao
interface ImageDao {
    @Query("SELECT * FROM mars")
    suspend fun getImages(): List<RoomImage>
}