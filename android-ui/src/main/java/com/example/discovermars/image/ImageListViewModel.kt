package com.example.discovermars.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discovermars.image.imagelist.ImageListEvent
import com.example.domain.AppCoroutineDispatchers
import com.example.domain.image.model.Image
import com.example.domain.usecases.OnGetImagesUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.FieldPosition
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val onGetImagesUseCase: OnGetImagesUseCase,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) : ViewModel() {

    private val imageListState = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = imageListState

    private val changeImageState = MutableLiveData<String>()
    val editImage: LiveData<String> get() = changeImageState


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
                onGetImagesUseCase.getImages()
            }
            imageListState.value = images
        }
    }

    private fun getImageDetail(position: Int) {
        changeImageState.value = imageList.value!![position].creationDate
    }
}