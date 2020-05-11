package com.example.discovermars.core.injection.module

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.cache.images.RoomImageDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Singleton
    @Provides
        fun provideRoomDatabase(
        applicationContext: Context
    ) : RoomImageDatabase {
        return Room.databaseBuilder(applicationContext, RoomImageDatabase::class.java, "marsdagger")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideChuckerInterecptor(context: Context): Interceptor{
        return ChuckerInterceptor(context)
    }
}