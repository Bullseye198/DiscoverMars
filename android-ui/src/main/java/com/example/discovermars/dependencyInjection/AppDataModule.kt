package com.example.discovermars.dependencyInjection

import com.example.data.image.ImageRepoImpl
import com.example.domain.image.IImageRepository
import dagger.Binds
import dagger.Module
import kotlin.coroutines.CoroutineContext

@Module
interface AppDataModule {


    @Binds
    fun bindCoroutineCOntext(coroutineContext: CoroutineContext): CoroutineContext
}