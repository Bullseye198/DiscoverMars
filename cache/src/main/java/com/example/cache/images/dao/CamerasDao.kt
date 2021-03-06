package com.example.cache.images.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.images.model.RoomCamera


@Dao
interface CamerasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCameras(entities: List<RoomCamera>)
}