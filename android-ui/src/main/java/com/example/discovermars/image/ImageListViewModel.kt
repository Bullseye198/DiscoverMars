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

    private val changeImageState = MutableLiveData<List<Image>>()
    val noteList: LiveData<List<Image>> get() = changeImageState

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

    }
}