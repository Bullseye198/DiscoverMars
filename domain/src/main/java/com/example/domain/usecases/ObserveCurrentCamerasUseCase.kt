package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.image.IImageRepository
import io.reactivex.Flowable
import javax.inject.Inject

class ObserveCurrentCamerasUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    rxSchedulers: AppRxSchedulers
): FlowableUseCase<List<String>, ObserveCurrentCamerasUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Params?): Flowable<List<String>> {
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