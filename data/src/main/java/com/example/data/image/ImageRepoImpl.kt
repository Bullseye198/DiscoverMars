package com.example.data.image

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepoImpl @Inject constructor(

) : IImageRepository{


    override suspend fun getImages(): List<Image> {
        return getImages()
    }


}