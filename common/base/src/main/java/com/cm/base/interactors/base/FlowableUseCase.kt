package com.cm.base.interactors.base

import com.cm.base.executor.AppRxSchedulers
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<T, in Params> constructor(
    private val rxSchedulers: AppRxSchedulers? = null
) {

    private val disposables = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: Params?): Flowable<T>

    open fun execute(observer: DisposableSubscriber<T>, params: Params?) {
        if (rxSchedulers != null) {
            buildRxSchedulerBasedDisposable(observer, params)
        } else {
            throw Exception("No Thread to execute defined.")
        }
    }

    private fun buildRxSchedulerBasedDisposable(
        observer: DisposableSubscriber<T>,
        params: Params?
    ) {
        if (disposables.size() > 0) {
            clear()
        }
        addDisposable(
            buildUseCaseObservable(params)
                .subscribeOn(rxSchedulers!!.io)
                .observeOn(rxSchedulers.main)
                .subscribeWith(observer)
        )
    }

    // current subscriptions removed, composite disposable does not allow further subscriptions
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    // current subscriptions are removed, composite disposable can still add new subscriptions
    fun clear() {
        disposables.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}