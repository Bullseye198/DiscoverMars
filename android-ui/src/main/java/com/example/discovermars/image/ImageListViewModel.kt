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

    private val imageState = MutableLiveData(ImageState())

    fun getState(): LiveData<ImageState> = imageState

    var currentCamera: String? = null
    var earthDate: String = ""
    var rover: String = ""

    init {
        getCurrentCameras()
        getImages()
        setupDefaultRoverAndDate()
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
        observeCurrentCamerasUseCase.onRoverChanged(newRover)
        refreshAndUpdate()
    }

    fun setupDefaultRoverAndDate() {
        val lastDate = LocalDate.now().minusDays(2)
        onDateSelected(lastDate.toString())
        onRoverSelected("Curiosity")
        onNewCameraSelected("FHAZ")
    }

    private fun getCurrentCameras() {
        observeCurrentCamerasUseCase.requestImages(object : DisposableSubscriber<List<String>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<String>?) {
                imageState.value = imageState.value!!.copy(cameras = t)
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
                imageState.value = imageState.value!!.copy(feed = t)
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed because ${t?.localizedMessage}.")
            }
        })
    }

    private fun refreshAndUpdate() {
        viewModelScope.launch {

            imageState.value = imageState.value!!.copy(loading = true)
            refreshImagesUseCase.refresh(earthDate, rover)
            imageState.value = imageState.value!!.copy(loading = false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        observeImagesUseCase.dispose()
    }
}