package com.example.discovermars.core.injection.module

import android.content.Context
import com.example.remote.injection.RemoteModuleBinds
import com.example.remote.photo.PhotoService
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module(includes = [RemoteModuleBinds::class])
object RemoteModule {

    @Provides
    fun providePhotoService(
        context: Context
    ): PhotoService {
        return PhotoServiceFactory.makePhotoService(context)
        //we need to return PhotoServiceFactory
    }
}