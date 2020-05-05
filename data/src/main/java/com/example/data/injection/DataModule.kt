package com.example.data.injection

import com.example.data.image.ImageRepoImpl
import com.example.domain.image.IImageRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindIImageRepository(imageRepoImpl: ImageRepoImpl): IImageRepository
}