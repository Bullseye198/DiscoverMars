package com.cm.base.interactors.base

import com.cm.base.executor.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

abstract class CoroutineCompletableUseCase<in Params> constructor(
    private val dispatchers: AppCoroutineDispatchers
) {

    protected abstract suspend fun execute(params: Params?)

    suspend fun invokeUseCase(params: Params?) {
        withContext(dispatchers.io) {
            execute(params)
        }
    }
}