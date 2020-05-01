package com.example.remote.photo

import com.example.data.image.PhotoRemote
import com.example.domain.image.model.Image
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRemoteImpl @Inject constructor(
    private val photoService: PhotoService
) : PhotoRemote {

    override suspend fun getImages(): List<Image> {
        return photoService.getCurrentPhoto("2019-6-3", "DEMO_KEY").photos
            .map {
                Image(it.earthDate, it.rover.name, it.imgSrc)
            }
    }
}