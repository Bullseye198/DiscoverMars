package com.example.cache.images.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.images.model.ImageAndRover
import com.example.cache.images.model.RoomImage
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM mars")
    suspend fun getImages(): List<RoomImage>

    @Query("SELECT * FROM mars WHERE creationDate=:earthDate AND contents=:rover")
    suspend fun getImagesForDate(earthDate: String, rover: String): List<RoomImage>

    @Query("SELECT * FROM mars WHERE creationDate=:earthDate AND contents=:rover")
    fun observeImages(earthDate: String, rover: String): Flow<List<ImageAndRover>>

    @Query("SELECT * FROM mars WHERE id=:id")
    fun observeImage(id: Int): Flow<ImageAndRover>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // or OnConflictStrategy.IGNORE
    suspend fun insertAllSuspend(entities: List<RoomImage>)
}