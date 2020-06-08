package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.example.domain.image.IImageRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class ObserveCurrentCamerasUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    private val rxSchedulers: AppRxSchedulers
) {

    private val disposable = CompositeDisposable()
    private val selectedDateStream: BehaviorSubject<String> = BehaviorSubject.create()
    private val selectedRoverStream: BehaviorSubject<String> = BehaviorSubject.create()

    fun requestImages(
        nina: DisposableSubscriber<List<String>>
    ) {
        val newSubscription = iImageRepository.observeImages()
            .combineLatest(selectedDateStream.toFlowable(BackpressureStrategy.LATEST))
            .combineLatest(selectedRoverStream.toFlowable(BackpressureStrategy.LATEST))
            .map { imagesAndSelectedDate ->
                val images = imagesAndSelectedDate.first.first
                val selectedDate = imagesAndSelectedDate.first.second
                val rover = imagesAndSelectedDate.second
                val imagesWithFilteredDateAndRover = images
                    .filter {
                        it.creationDate == selectedDate &&
                                it.contents == rover
                    }
                val uniqueCamerasForRoverAndDate = imagesWithFilteredDateAndRover
                    .mapNotNull { it.camera?.name }
                    .distinct()
                uniqueCamerasForRoverAndDate
            }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(nina)
        disposable.add(newSubscription)
    }

    fun onDateChanged(newDate: String) {
        selectedDateStream.onNext(newDate)
    }

    fun onRoverChanged(newRover: String) {
        selectedRoverStream.onNext(newRover)
    }

    fun dispose() {
        disposable.dispose()
    }
}