package com.example.discovermars.image

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ImageListViewModel @Inject constructor(

) : ViewModel()
{
    // Implement viewModel

    private fun getImages() {
        val imagesResult = listOf( "Marco", "Christoph", "Martin")
    }
}