package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class ObserveImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    private val rxSchedulers: AppRxSchedulers
) {

    private val disposable = CompositeDisposable()
    private val selectedCameraStream: BehaviorSubject<String> = BehaviorSubject.create()

    fun requestImages(
        nina: DisposableSubscriber<List<Image>>,
        selectedDate: String, rover: String
    ) {
        disposable.clear()
        val newSubscription = iImageRepository.observeImages(selectedDate, rover)
            .combineLatest(selectedCameraStream.toFlowable(BackpressureStrategy.LATEST))
            .map { imagesAndSelectedDate ->
                val images = imagesAndSelectedDate.first
                val selectedCamera = imagesAndSelectedDate.second

                val imagesForRoverDateAndCamera = images.filter {
                    it.camera?.name == selectedCamera
                }
                imagesForRoverDateAndCamera
            }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(nina)
        disposable.add(newSubscription)
    }

    fun onSelectedCameraChanged(newCamera: String) {
        selectedCameraStream.onNext(newCamera)
    }


    fun dispose() {
        disposable.dispose()
    }
}