package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.Flowable
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class OnGetImageByIdUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    private val rxSchedulers: AppRxSchedulers
) {
    fun getImage(josh: DisposableSubscriber<Image>, ImageId: Int) {
        iImageRepository.observeImages()
            .map { images -> images.first { it.id == ImageId } }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(josh)
    }
}