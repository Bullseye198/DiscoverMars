package com.example.discovermars.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discovermars.image.imagelist.ImageListEvent
import com.example.domain.AppCoroutineDispatchers
import com.example.domain.image.model.Image
import com.example.domain.usecases.RefreshImagesUseCase
import com.example.domain.usecases.RequestImagesUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val requestImagesUseCase: RequestImagesUseCase,
    private val refreshImagesUseCase: RefreshImagesUseCase,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) : ViewModel() {

    private val imageListState = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = imageListState

    private val changeImageState = MutableLiveData<String>()
    val editImage: LiveData<String> get() = changeImageState

    init {
        getImages()
    }

    //TODO: Check out this handle event and use it.
    fun handleEvent(event: ImageListEvent) {
        when (event) {
            is ImageListEvent.OnImageItemClick -> getImageDetail(event.position)
            is ImageListEvent.OnStart -> getImages()
        }
    }

    private fun getImages() {
        viewModelScope.launch {
            val images = withContext(appCoroutineDispatchers.io) {
                requestImagesUseCase.requestImages()
            }
            imageListState.value = images
        }
        refreshAndUpdate()
    }

    private fun refreshAndUpdate(){
        viewModelScope.launch {
            val images = withContext(appCoroutineDispatchers.io) {
                refreshImagesUseCase.refresh()
                requestImagesUseCase.requestImages()
            }
            imageListState.value = images
        }
    }

    private fun getImageDetail(position: Int) {
        changeImageState.value = imageList.value!![position].creationDate
    }
}