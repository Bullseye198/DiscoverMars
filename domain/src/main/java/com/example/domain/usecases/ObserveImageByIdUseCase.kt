package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.FlowUseCase
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ObserveImageByIdUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : FlowUseCase<Image, Int>(appCoroutineDispatchers) {

    override fun buildStream(params: Int?): Flow<Image> {
        return iImageRepository.observeImage(params!!)
    }

    data class GetImageParams(
        val imageId: Int
    )
}