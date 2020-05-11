package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    val appDispatchers: AppCoroutineDispatchers

) {

    suspend fun requestImages(earthDate: String? = null, camera: String? = null): List<Image> {
        return withContext(appDispatchers.io) { iImageRepository.requestImages(earthDate, camera) }
    }

}