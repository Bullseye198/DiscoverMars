package com.example.cache.injection

import com.example.cache.PhotoCacheImpl
import com.example.data.image.PhotoCache
import dagger.Binds
import dagger.Module

@Module
interface CacheBindsModule {

    @Binds
    fun bindPhotosCached(photoCacheImpl: PhotoCacheImpl): PhotoCache
}