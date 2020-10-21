package com.example.discovermars.image.imagedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.interactors.base.FlowUseCase
import com.cm.base.mvviewmodel.MvViewModel
import com.example.discovermars.image.ImageState
import com.example.domain.image.model.Image
import com.example.domain.usecases.ObserveImageByIdUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onErrorCollect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val observeImageByIdUseCase: ObserveImageByIdUseCase
) : MvViewModel<ImageState>(
    ImageState()
) {

    private val imageState = MutableLiveData(ImageState())


    fun getState(): LiveData<ImageState> = imageState

    fun handleEvent(event: ImageDetailEvent) {
        when (event) {
            is ImageDetailEvent.OnStart -> getImage(event.imageId)
        }
    }

    @ExperimentalCoroutinesApi
    private fun getImage(ImageId: Int) {
        viewModelScope.launch {
            observeImageByIdUseCase.buildStream(ImageId)
                .catch {}
                .collect { image ->
                    imageState.value = imageState.value!!.copy(image = image)
                }
        }
    }
}