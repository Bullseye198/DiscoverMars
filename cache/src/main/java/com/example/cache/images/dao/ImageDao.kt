package com.example.cache.images.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.images.RoomImage
import io.reactivex.Flowable

@Dao
interface ImageDao {
    @Query("SELECT * FROM mars")
    suspend fun getImages(): List<RoomImage>

    @Query("SELECT * FROM mars WHERE camera=:camera")
    suspend fun getImagesForCamera(camera: String): List<RoomImage>

    @Query("SELECT * FROM mars")
    fun observeImages(): Flowable<List<RoomImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // or OnConflictStrategy.IGNORE
    suspend fun insertAllSuspend(entities: List<RoomImage>)
}