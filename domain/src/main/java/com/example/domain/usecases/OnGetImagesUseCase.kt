package com.example.domain.usecases

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import javax.inject.Inject

class OnGetImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository
){

    suspend fun getImages(): List<Image> {
       return iImageRepository.getImages()
    }
}