package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.FlowUseCase
import com.example.domain.image.IImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveCurrentCamerasUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
): FlowUseCase<List<String>, ObserveCurrentCamerasUseCase.Params>(appCoroutineDispatchers) {

    override fun buildStream(params: Params?): Flow<List<String>> {
        return iImageRepository.observeImages(params!!.earthDate, params.rover)
            .map { images ->
                val imagesWithFilteredDateAndRover = images
                    .filter {
                        it.creationDate == params.earthDate &&
                                it.contents == params.rover
                    }
                val uniqueCamerasForRoverAndDate = imagesWithFilteredDateAndRover
                    .mapNotNull { it.camera?.name }
                    .distinct()
                uniqueCamerasForRoverAndDate
            }
    }

    data class Params(
        val earthDate: String,
        val rover: String
    )
}