package com.example.domain.usecases

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import javax.inject.Inject

class RequestImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository
){

    suspend fun requestImages(camera: String? = null): List<Image>{
        return iImageRepository.requestImages(camera)
    }

}