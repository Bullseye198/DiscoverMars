package com.example.domain.usecases

import com.example.domain.image.IImageRepository
import javax.inject.Inject

class RefreshImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository
) {
    suspend fun refresh() {
        try {
            val serverImages = iImageRepository.fetchImages(camera = "")
            iImageRepository.storeImages(serverImages)
        } catch (e: Exception) {
        }

    }
}