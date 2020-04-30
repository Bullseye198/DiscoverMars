package com.example.remote.injection

import com.example.remote.photo.PhotoService
import com.example.remote.photo.PhotoServiceFactory
import dagger.Module

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module(includes = [RemoteModuleBinds::class])
object RemoteModule {

    @Provides
    fun providePhotoService(
    ): PhotoService {
        //we need to return PhotoServiceFactory
    }
}