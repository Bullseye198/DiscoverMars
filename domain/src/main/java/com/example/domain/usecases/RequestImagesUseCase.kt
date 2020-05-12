package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.executor.AppRxSchedulers
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber
import java.awt.Composite
import javax.inject.Inject

class RequestImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    private val rxSchedulers: AppRxSchedulers
) {

    private val disposable = CompositeDisposable()

    fun requestImages(
        nina: DisposableSubscriber<List<Image>>,
        earthDate: String? = null,
        camera: String? = null
    ) {
        val newSubscription = iImageRepository.observeImages()
            .map { images ->
                images.filter {
                    it.creationDate == earthDate &&
                            it.camera == camera
                }
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