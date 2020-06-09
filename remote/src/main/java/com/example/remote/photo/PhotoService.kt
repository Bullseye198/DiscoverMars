package com.example.remote.photo

import com.example.remote.photo.model.Images
import com.example.remote.photo.model.RoverRaw
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the Photo Service.
 */

//https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=DEMO_KEY
interface PhotoService {

    @GET("rovers/{rover}/photos")
    suspend fun getCurrentPhoto(
        @Path("rover") rover: String,
        @Query("earth_date") earthDate: String,
        @Query("api_key") key: String
    ): Images
}