package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import io.reactivex.Flowable
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class ObserveImageByIdUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    private val rxSchedulers: AppRxSchedulers
) : FlowableUseCase<Image, Int>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Int?): Flowable<Image> {
        return iImageRepository.observeImage(params!!)
    }
}