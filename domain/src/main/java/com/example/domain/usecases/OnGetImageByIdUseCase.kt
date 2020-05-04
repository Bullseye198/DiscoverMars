package com.example.domain.usecases

import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import javax.inject.Inject

class OnGetImageByIdUseCase @Inject constructor(
    private val iImageRepository: IImageRepository
) {
    suspend fun getImage(ImageId : Int): Image {
        return iImageRepository.getImageById(ImageId)
    }
}