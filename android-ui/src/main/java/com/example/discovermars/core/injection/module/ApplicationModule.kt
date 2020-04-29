package com.example.discovermars.core.injection.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
@Module
object ApplicationModule {

    @Singleton
    @Provides
        fun provideRoomDatabase(
        applicationContext: Context
    ) : RoomDatabase {
        return Room.databaseBuilder(applicationContext, RoomDatabase::class.java, "marsdagger")
            .build()
    }

    @Singleton
    @Provides
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

}