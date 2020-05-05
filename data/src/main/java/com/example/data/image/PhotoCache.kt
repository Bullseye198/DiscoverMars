package com.example.data.image

import com.example.domain.image.model.Image


interface PhotoCache {
    suspend fun getImagesSuspend(): List<Image>
}