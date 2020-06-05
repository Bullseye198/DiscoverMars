package com.example.cache.images.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.images.model.RoomRover

@Dao
interface RoverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRover(entities: List<RoomRover>)
}