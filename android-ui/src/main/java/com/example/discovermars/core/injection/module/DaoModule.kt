package com.example.discovermars.core.injection.module

import com.example.cache.images.RoomImageDatabase
import dagger.Module
import dagger.Provides

@Module
object DaoModule {

    @Provides
    fun provideImageDao(roomImageDatabase: RoomImageDatabase) = roomImageDatabase.roomImageDao()

    @Provides
    fun provideCamerasDao(roomImageDatabase: RoomImageDatabase) = roomImageDatabase.roomCamerasDao()

    @Provides
    fun provideRoverDao(roomImageDatabase: RoomImageDatabase) = roomImageDatabase.roomRoverDao()

}