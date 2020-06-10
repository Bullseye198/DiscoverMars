package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.image.IImageRepository
import javax.inject.Inject

class RefreshImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    appDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshImagesUseCase.Params>(appDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val serverImages =
                iImageRepository.fetchImages(params!!.earthDate, params.rover)
            iImageRepository.storeImages(serverImages)
        } catch (e: Exception) {
        }
    }

    data class Params(
        val earthDate: String,
        val rover: String
    )
}