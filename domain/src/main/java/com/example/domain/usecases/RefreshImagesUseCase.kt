package com.example.domain.usecases

import com.example.domain.image.IImageRepository
import javax.inject.Inject

class RefreshImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository
) {
    suspend fun refresh() {
        try {
            val serverImages = iImageRepository.fetchImages(earthDate = "2015-5-6", camera = "")
            iImageRepository.storeImages(serverImages)
        } catch (e: Exception) {
        }

    }
}