package com.example.discovermars.image.imagedetail

sealed class ImageDetailEvent {
    data class OnStart(val imageId: Int): ImageDetailEvent()
}