package com.example.cache.injection

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
    CacheBindsModule::class]
)

object CacheModule {


}