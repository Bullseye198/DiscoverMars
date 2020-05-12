package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.executor.AppRxSchedulers
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class RequestImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    private val rxSchedulers: AppRxSchedulers
) {

    fun requestImages(
        nina: DisposableSubscriber<List<Image>>,
        earthDate: String? = null,
        camera: String? = null
    ) {
        iImageRepository.observeImages()
            .map { images ->
                images.filter {
                    it.creationDate == earthDate &&
                            it.camera == camera
                }
            }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(nina)

    }

}