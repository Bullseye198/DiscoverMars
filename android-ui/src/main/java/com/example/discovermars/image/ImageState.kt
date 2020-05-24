package com.example.discovermars.image

import com.example.domain.image.model.Image

data class ImageState (

    val feed: List<Image>? = null,
    var loading: Boolean = false,
    var cameras: List<String>? = null
)