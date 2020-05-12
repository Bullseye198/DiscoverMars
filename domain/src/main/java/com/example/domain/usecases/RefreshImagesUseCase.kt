package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.GetUseCase
import com.example.domain.image.IImageRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RefreshImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    val appDispatchers: AppCoroutineDispatchers
) {
    suspend fun refresh(earthDate: String) {
        withContext(appDispatchers.io) {
            try {
                val serverImages = iImageRepository.fetchImages(earthDate = earthDate, camera = "")
                iImageRepository.storeImages(serverImages)
            } catch (e: Exception) {
            }
        }
    }
}