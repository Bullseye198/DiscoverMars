package com.cm.base.mvviewmodel

import com.cm.baseAndroid.SingleLiveEvent

sealed class ViewModelState<out ViewModelData>(
    val data: ViewModelData,
    val errorMessage: SingleLiveEvent<Throwable>? = null
) {

    class ErrorButBackupData<out ViewModelData>(
        data: ViewModelData,
        errorMessage: SingleLiveEvent<Throwable>? = null
    ) : ViewModelState<ViewModelData>(data, errorMessage)

    class ErrorAndNoBackupData<out ViewModelData>(
        data: ViewModelData,
        errorMessage: SingleLiveEvent<Throwable>? = null
    ) : ViewModelState<ViewModelData>(data, errorMessage)

    class Success<out ViewModelData>(
        data: ViewModelData
    ) : ViewModelState<ViewModelData>(data, null)

    class Idle<out ViewModelData>(
        data: ViewModelData
    ) : ViewModelState<ViewModelData>(data, null)
}
