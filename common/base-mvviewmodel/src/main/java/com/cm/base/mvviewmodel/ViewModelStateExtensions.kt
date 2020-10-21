package com.cm.base.mvviewmodel

import androidx.lifecycle.MutableLiveData


fun <T> MutableLiveData<ViewModelState<T>>.initialize(data: T) {
    value = ViewModelState.Idle(data)
}

fun <S> MvViewModel<S>.setSuccessData(data: S) {
    state.value = ViewModelState.Success(data)
}

fun <S> MvViewModel<S>.requireData(): S {
    return state.value?.data
        ?: throw IllegalStateException("ViewModel state value accessed before initialized")
}
