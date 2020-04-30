package com.example.remote.photo

import retrofit2.Retrofit


class PhotoRemoteImpl {

    val photoService: PhotoService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("/https://api.nasa.gov/mars-photos/api/v1/")
            .build()

        photoService = retrofit.create(PhotoService::class.java)
       val images = photoService.getCurrentPhoto("DEMO_KEY")
    }
}