package com.example.discovermars.image.imagelist


sealed class ImageListEvent {
    data class OnImageItemClick(val position: Int, val imageId: Int): ImageListEvent()
    object OnStart : ImageListEvent()
}