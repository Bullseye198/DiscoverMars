package com.example.discovermars.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discovermars.image.imagelist.ImageListEvent
import com.example.domain.image.model.Image
import com.example.domain.usecases.ObserveCurrentCamerasUseCase
import com.example.domain.usecases.RefreshImagesUseCase
import com.example.domain.usecases.RequestImagesUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val requestImagesUseCase: RequestImagesUseCase,
    private val observeCurrentCamerasUseCase: ObserveCurrentCamerasUseCase,
    private val refreshImagesUseCase: RefreshImagesUseCase
) : ViewModel() {

    private val imageListState = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = imageListState

    private val cameraLiveData = MutableLiveData<List<String>>()
    val cameras: LiveData<List<String>> get() = cameraLiveData

    private val changeImageState = MutableLiveData<String>()
    val editImage: LiveData<String> get() = changeImageState

    var currentCamera: String? = null
    var earthDate: String = ""

    init {
        getCurrentCameras()
        getImages()
        onLatestImages()
    }

    fun handleEvent(event: ImageListEvent) {
        when (event) {
            is ImageListEvent.OnImageItemClick -> getImageDetail(event.position)
            is ImageListEvent.OnStart -> getImages()
        }
    }

    fun onNewCameraSelected(newCamera: String) {
        currentCamera = newCamera
        requestImagesUseCase.onSelectedCameraChanged(newCamera)
    }

    fun onDateSelected(earthDate: String) {
        this.earthDate = earthDate
        requestImagesUseCase.onDateChanged(earthDate)
        observeCurrentCamerasUseCase.onDateChanged(earthDate)
        refreshAndUpdate()
    }

    fun onLatestImages() {
        val lastDate = LocalDate.now().minusDays(2)
        onDateSelected(lastDate.toString())
    }

    private fun getCurrentCameras() {
        observeCurrentCamerasUseCase.requestImages(object : DisposableSubscriber<List<String>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<String>?) {
                cameraLiveData.value = t
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed because ${t?.localizedMessage}.")
            }

        })
    }

    private fun getImages() {
        requestImagesUseCase.requestImages(object : DisposableSubscriber<List<Image>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<Image>?) {
                imageListState.value = t
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed because ${t?.localizedMessage}.")
            }
        })
    }

    private fun refreshAndUpdate() {
        viewModelScope.launch {
            //val loading = true
            refreshImagesUseCase.refresh(earthDate)
            //loading = false
        }
    }

    private fun getImageDetail(position: Int) {
        changeImageState.value = imageList.value!![position].creationDate
    }

    override fun onCleared() {
        super.onCleared()
        requestImagesUseCase.dispose()
    }
}