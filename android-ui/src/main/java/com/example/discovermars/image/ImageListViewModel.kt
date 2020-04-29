package com.example.discovermars.image

import androidx.lifecycle.ViewModel
import com.example.discovermars.image.imagelist.ImageListEvent
import com.example.domain.image.model.Image
import javax.inject.Inject

class ImageListViewModel @Inject constructor(

) : ViewModel() {

     fun handleEvent(event: ImageListEvent) {
        when(event) {
            is ImageListEvent.OnStart -> getImage()
        }
    }



    private fun getImage() {
        val imageResult = listOf("Marco", "Christoph", "Martin")
    }

}
