package com.example.remote.photo

import com.example.remote.injection.RemoteModuleBinds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module(includes = [RemoteModuleBinds::class])
object RemoteModule {

    @Provides
    @Singleton
    fun providePhotoService(
        chuckerInterceptor: Interceptor
    ): PhotoService {
        return PhotoServiceFactory.makePhotoService(
            chuckerInterceptor
        )
        //we need to return PhotoServiceFactory
    }
}