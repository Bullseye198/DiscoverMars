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

    fun requestImages(
        nina: DisposableSubscriber<List<String>>,
        selectedDate: String, rover: String
    ) {
        disposable.clear()
        val newSubscription = iImageRepository.observeImages(selectedDate, rover)
            .map { images ->
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

    fun dispose() {
        disposable.dispose()
    }
}