package com.example.discovermars.image.imagelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discovermars.image.ImageState
import com.example.domain.image.model.Image
import com.example.domain.usecases.ObserveCurrentCamerasUseCase
import com.example.domain.usecases.RefreshImagesUseCase
import com.example.domain.usecases.ObserveImagesUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ImageListViewModel @Inject constructor(
    private val observeImagesUseCase: ObserveImagesUseCase,
    private val observeCurrentCamerasUseCase: ObserveCurrentCamerasUseCase,
    private val refreshImagesUseCase: RefreshImagesUseCase
) : ViewModel() {

    private val imageState = MutableLiveData(ImageState())

    fun getState(): LiveData<ImageState> = imageState

    var earthDate: String = ""
    var rover: String = ""

    init {
        getCurrentCameras()
        getImages()
        setupDefaultRoverAndDate()
    }


    @ExperimentalCoroutinesApi
    fun onNewCameraSelected(newCamera: String) {
        observeImagesUseCase.onSelectedCameraChanged(newCamera)
    }

    @ExperimentalCoroutinesApi
    fun onDateSelected(earthDate: String) {
        this.earthDate = earthDate
        getCurrentCameras()
        getImages()
        refreshAndUpdate()
    }

    fun onRoverSelected(newRover: String) {
        rover = newRover
        getCurrentCameras()
        getImages()
        refreshAndUpdate()
    }

    @ExperimentalCoroutinesApi
    fun setupDefaultRoverAndDate() {
        val lastDate = LocalDate.now().minusDays(2)
        onDateSelected(lastDate.toString())
        onRoverSelected("Curiosity")
        onNewCameraSelected("FHAZ")
    }

    private fun getCurrentCameras() {
        viewModelScope.launch {
            observeCurrentCamerasUseCase.buildStream(
                ObserveCurrentCamerasUseCase.Params(
                    earthDate,
                    rover
                )
            )
                .catch { }
                .collect { currentCameras ->
                    imageState.value = imageState.value!!.copy(cameras = currentCameras)
                }
        }
    }

    @ExperimentalCoroutinesApi
    private fun getImages() {
        viewModelScope.launch {
            observeImagesUseCase.buildStream(ObserveImagesUseCase.Params(rover, earthDate))
                .catch { }
                .collect { images ->
                    imageState.value = imageState.value!!.copy(feed = images)
                }
        }
    }

    private fun refreshAndUpdate() {
        viewModelScope.launch {

            imageState.value = imageState.value!!.copy(loading = true)
            refreshImagesUseCase.invokeUseCase(RefreshImagesUseCase.Params(earthDate, rover))
            imageState.value = imageState.value!!.copy(loading = false)
        }
    }
}