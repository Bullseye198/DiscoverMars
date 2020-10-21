package com.cm.base.mvviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.interactors.base.FlowUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MvViewModel<S>(s: S) : ViewModel() {

    val state = MutableLiveData<ViewModelState<S>>().apply {
        initialize(s)
    }


    inline fun <T, V> FlowUseCase<T, V>.executeWithViewState(
        params: V,
        crossinline stateReducer: S.(t: T) -> S
    ) {
        viewModelScope.launch {
            buildUseCaseFlow(params)
                .catch { handleException(it) }
                .collect { setSuccessData(stateReducer(requireData(), it)) }
        }

    }

    fun handleException(e: Throwable?) {
        if (BuildConfig.DEBUG) {
            //       throw Exception(e)
        }
    }
}