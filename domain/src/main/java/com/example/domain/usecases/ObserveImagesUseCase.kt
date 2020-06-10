package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ObserveImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<List<Image>, ObserveImagesUseCase.Parameters>(rxSchedulers) {

    private val selectedCameraStream: BehaviorSubject<String> = BehaviorSubject.create()

    override fun buildUseCaseObservable(params: Parameters?): Flowable<List<Image>> {

        return iImageRepository.observeImages(params!!.earthDate, params.rover)
            .combineLatest(selectedCameraStream.toFlowable(BackpressureStrategy.LATEST))
            .map { imagesAndSelectedDate ->
                val images = imagesAndSelectedDate.first
                val selectedCamera = imagesAndSelectedDate.second

                val imagesForRoverDateAndCamera = images.filter {
                    it.camera?.name == selectedCamera
                }
                imagesForRoverDateAndCamera
            }
    }

    fun onSelectedCameraChanged(newCamera: String) {
        selectedCameraStream.onNext(newCamera)
    }

    data class Parameters(
        val rover: String,
        val earthDate: String
    )
}