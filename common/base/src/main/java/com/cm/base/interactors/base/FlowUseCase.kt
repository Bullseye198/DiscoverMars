package com.cm.base.interactors.base

import com.cm.base.executor.AppCoroutineDispatchers
import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<T, in Params> constructor(
    private val dispatchers: AppCoroutineDispatchers
) {
    abstract fun buildStream(params: Params?): Flow<T>

    fun buildUseCaseFlow(params: Params?): Flow<T> {
        return buildStream(params)
    }
}