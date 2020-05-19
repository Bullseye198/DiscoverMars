package com.example.discovermars.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discovermars.image.imagelist.ImageListEvent
import com.example.domain.image.model.Image
import com.example.domain.usecases.ObserveCurrentCamerasUseCase
import com.example.domain.usecases.RefreshImagesUseCase
import com.example.domain.usecases.ObserveImagesUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val observeImagesUseCase: ObserveImagesUseCase,
    private val observeCurrentCamerasUseCase: ObserveCurrentCamerasUseCase,
    private val refreshImagesUseCase: RefreshImagesUseCase
) : ViewModel() {

    private val imageListState = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = imageListState

    private val cameraLiveData = MutableLiveData<List<String>>()
    val cameras: LiveData<List<String>> get() = cameraLiveData

    private val changeImageState = MutableLiveData<String>()

    private val loadingState = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = loadingState

    var currentCamera: String? = null
    var earthDate: String = ""
    var rover: String = ""

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
        observeImagesUseCase.onSelectedCameraChanged(newCamera)
    }

    fun onDateSelected(earthDate: String) {
        this.earthDate = earthDate
        observeImagesUseCase.onDateChanged(earthDate)
        observeCurrentCamerasUseCase.onDateChanged(earthDate)
        refreshAndUpdate()
    }

    fun onRoverSelected(newRover: String) {
        rover = newRover
        observeImagesUseCase.onSelectedRoverChanged(newRover)
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
        observeImagesUseCase.requestImages(object : DisposableSubscriber<List<Image>>() {
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

            loadingState.value = true
            refreshImagesUseCase.refresh(earthDate, rover)
            loadingState.value = false
        }
    }

    private fun getImageDetail(position: Int) {
        changeImageState.value = imageList.value!![position].creationDate
    }

    override fun onCleared() {
        super.onCleared()
        observeImagesUseCase.dispose()
    }
}