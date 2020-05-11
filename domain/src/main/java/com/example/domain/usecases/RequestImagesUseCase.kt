package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.executor.AppRxSchedulers
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    val appDispatchers: AppCoroutineDispatchers,
    private val rxSchedulers: AppRxSchedulers
) {

     fun requestImages(nina: DisposableSubscriber<Image>,
                       earthDate: String? = null,
                       camera: String? = null) {
       // return withContext(appDispatchers.io) { iImageRepository.requestImages(earthDate, camera) }
         iImageRepository.observeImages()
             .map { images -> images.first { it.creationDate == earthDate &&
                     it.camera == camera} }
             .subscribeOn(rxSchedulers.io)
             .observeOn(rxSchedulers.main)
             .subscribeWith(nina)

    }

}