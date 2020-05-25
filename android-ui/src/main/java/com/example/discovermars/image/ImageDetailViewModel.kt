package com.example.discovermars.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.discovermars.image.imagedetail.ImageDetailEvent
import com.example.domain.image.model.Image
import com.example.domain.usecases.OnGetImageByIdUseCase
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val onGetImageByIdUseCase: OnGetImageByIdUseCase
) : ViewModel() {

    private val imageState = MutableLiveData(ImageState())

    fun getState(): LiveData<ImageState> = imageState

    fun handleEvent(event: ImageDetailEvent) {
        when (event) {
            is ImageDetailEvent.OnStart -> getImage(event.imageId)
        }
    }

    private fun getImage(ImageId: Int) {
        onGetImageByIdUseCase.getImage(object : DisposableSubscriber<Image>() {
            override fun onComplete() {
            }

            override fun onNext(t: Image?) {
                imageState.value = imageState.value!!.copy(image = t)
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed because ${t?.localizedMessage}.")
            }
        }, ImageId)
    }
}