package com.example.data.image

import com.example.domain.image.model.Image

interface PhotoRemote {

    suspend fun fetchImages(earthDate: String, rover: String): List<Image>

}