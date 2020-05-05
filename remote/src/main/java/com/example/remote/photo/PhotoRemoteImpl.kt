package com.example.remote.photo

import com.example.data.image.PhotoRemote
import com.example.domain.image.model.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRemoteImpl @Inject constructor(
    private val photoService: PhotoService
) : PhotoRemote {

    override suspend fun fetchImages(): List<Image> {
        return photoService.getCurrentPhoto("2019-8-3", "DEMO_KEY").photos
            .map {
                Image(it.earthDate, it.rover.name, it.imgSrc, it.id)
            }
    }


}