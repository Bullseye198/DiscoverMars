package com.example.data.injection

import com.example.domain.image.IImageRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindIImageRepository(iImageRepository: IImageRepository): IImageRepository
}