package com.example.remote.injection

import com.example.data.image.PhotoRemote
import com.example.remote.photo.PhotoRemoteImpl
import dagger.Binds
import dagger.Module

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
interface RemoteModuleBinds {

    @Binds
    fun bindPhotoRemote(photoRemoteImpl: PhotoRemoteImpl): PhotoRemote
}