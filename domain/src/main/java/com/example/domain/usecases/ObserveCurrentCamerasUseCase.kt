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

    init {
        selectedDateStream.onNext("")
    }

    fun requestImages(
        nina: DisposableSubscriber<List<String>>
    ) {
        val newSubscription = iImageRepository.observeImages()
            .combineLatest(selectedDateStream.toFlowable(BackpressureStrategy.LATEST))
            .map { imagesAndSelectedDate ->
                val images = imagesAndSelectedDate.first
                val selectedDate = imagesAndSelectedDate.second
                val cameras = images
                    .filter { it.creationDate == selectedDate }
                    .map { it.camera }
                    .distinct()
                cameras
            }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(nina)
        disposable.add(newSubscription)
    }

    fun onDateChanged(newDate: String) {
        selectedDateStream.onNext(newDate)
    }

    fun dispose() {
        disposable.dispose()
    }
}