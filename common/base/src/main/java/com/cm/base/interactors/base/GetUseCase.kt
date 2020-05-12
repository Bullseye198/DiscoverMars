package com.cm.base.interactors.base

import com.cm.base.executor.AppRxSchedulers
import io.reactivex.Flowable

/**Retrieves changes from the data layer.
 */
abstract class GetUseCase<T, in Params> constructor(
    rxSchedulers: AppRxSchedulers? = null
) : FlowableUseCase<T, Params>(rxSchedulers) {

    abstract fun buildStream(params: Params?): Flowable<T>

    override fun buildUseCaseObservable(params: Params?): Flowable<T> {
        return buildStream(params)
    }

}