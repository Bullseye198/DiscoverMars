package com.example.discovermars.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.discovermars.image.imagelist.ImageListEvent
import com.example.domain.image.model.Image
import java.text.FieldPosition
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
) : ViewModel() {

    private val imageListState = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = imageListState

    private val changeImageState = MutableLiveData<String>()
    val editImage: LiveData<String> get() = changeImageState


//TODO: Check out this handle event and use it.
     fun handleEvent(event: ImageListEvent) {
        when(event) {
            is ImageListEvent.OnImageItemClick -> getImageDetail(event.position)
            is ImageListEvent.OnStart -> getImage()
        }
    }



    private fun getImage() {
        val imageResult = listOf("Marco", "Christoph", "Martin")
    }

    private fun getImageDetail(position: Int) {
    changeImageState.value = imageList.value!![position].creationDate
    }
}