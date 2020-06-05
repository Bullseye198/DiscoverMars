package com.example.cache.images.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.images.model.ImageAndRover
import com.example.cache.images.model.RoomImage
import io.reactivex.Flowable

@Dao
interface ImageDao {
    @Query("SELECT * FROM mars")
    suspend fun getImages(): List<RoomImage>

    @Query("SELECT * FROM mars WHERE creationDate=:earthDate AND contents=:rover")
    suspend fun getImagesForDate(earthDate: String, rover: String): List<RoomImage>

    @Query("SELECT * FROM mars WHERE creationDate=:earthDate AND camera=:camera AND contents=:rover")
    suspend fun getImagesForCameraAndDate(
        earthDate: String,
        camera: String,
        rover: String
    ): List<RoomImage>


    @Query("SELECT * FROM mars WHERE camera=:camera AND contents=:rover")
    suspend fun getImagesForCamera(camera: String, rover: String): List<RoomImage>


    @Query("SELECT * FROM mars")
    fun observeImages(): Flowable<List<RoomImage>>

    @Query("SELECT * FROM mars WHERE id=:id")
    fun observeImage(id: String): Flowable<ImageAndRover>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // or OnConflictStrategy.IGNORE
    suspend fun insertAllSuspend(entities: List<RoomImage>)
}