package com.example.discovermars.image

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discovermars.image.imagedetail.ImageDetailEvent
import com.example.domain.image.model.Image
import com.example.domain.usecases.OnGetImageByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val onGetImageByIdUseCase: OnGetImageByIdUseCase
) : ViewModel(){

    private val imageState = MutableLiveData<Image>()
    val image: LiveData<Image> get() = imageState

     fun handleEvent(event: ImageDetailEvent) {
        when(event) {
            is ImageDetailEvent.OnStart -> getImage(event.imageId)
        }
    }

    private fun getImage(ImageId: Int) {
        viewModelScope.launch {
           val imageResult =  onGetImageByIdUseCase.getImage(ImageId)

           imageState.value = imageResult
        }
    }
}