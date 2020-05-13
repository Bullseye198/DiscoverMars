package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.executor.AppRxSchedulers
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subscribers.DisposableSubscriber
import java.awt.Composite
import java.time.LocalDate
import javax.inject.Inject

class RequestImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    private val rxSchedulers: AppRxSchedulers
) {

    private val disposable = CompositeDisposable()
    val selectedDateStream: BehaviorSubject<String> = BehaviorSubject.create()
    val selectedCameraStream: BehaviorSubject<String> = BehaviorSubject.create()
    val today = LocalDate.now()

    init {
        selectedDateStream.onNext("${today.year}-${today.month}-${today.dayOfMonth}")
        selectedDateStream.onNext("")
    }

    fun requestImages(
        nina: DisposableSubscriber<List<Image>>) {
        val newSubscription = iImageRepository.observeImages()
            .combineLatest(selectedDateStream.toFlowable(BackpressureStrategy.LATEST))
            .combineLatest(selectedCameraStream.toFlowable(BackpressureStrategy.LATEST))
            .map { imagesAndSelectedDate ->
                val images = imagesAndSelectedDate.first.first
                val selectedDate = imagesAndSelectedDate.first.second
                val camera = imagesAndSelectedDate.second
                images.filter {
                    it.creationDate == selectedDate &&
                            it.camera == camera
                }
            }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(nina)
        disposable.add(newSubscription)
    }

    fun onDateChanged(newDate: String){
        selectedDateStream.onNext(newDate)
    }

    fun onSelectedCameraChanged(newCamera: String){
        selectedCameraStream.onNext(newCamera)
    }

    fun dispose() {
        disposable.dispose()
    }
}