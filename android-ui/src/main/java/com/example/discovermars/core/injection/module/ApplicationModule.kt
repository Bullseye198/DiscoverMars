package com.example.discovermars.core.injection.module

import android.content.Context
import com.example.domain.AppCoroutineDispatchers
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

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