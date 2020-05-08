package com.example.domain.usecases

import com.example.domain.image.IImageRepository
import javax.inject.Inject

class RefreshImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository
) {
    suspend fun refresh(earthDate: String) {
        try {
            val serverImages = iImageRepository.fetchImages(earthDate = earthDate, camera = "")
            iImageRepository.storeImages(serverImages)
        } catch (e: Exception) {
        }

    }
}