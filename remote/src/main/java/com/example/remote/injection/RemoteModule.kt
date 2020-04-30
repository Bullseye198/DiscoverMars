package com.example.remote.injection

import dagger.Module

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module(includes = [RemoteModuleBinds::class])
object RemoteModule {
}