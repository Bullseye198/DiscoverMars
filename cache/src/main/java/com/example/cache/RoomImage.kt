package com.example.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "mars",
    indices = [Index("creation_date")]
)
data class RoomImage (
    @PrimaryKey
    @ColumnInfo(name = "creation_date")
    val creationDate: String
)

