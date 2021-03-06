package com.example.remote.photo.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "camera")
    val camera: CameraRaw,
    @Json(name = "earth_date")
    val earthDate: String, // 2015-06-03
    @Json(name = "id")
    val id: Int, // 102685
    @Json(name = "img_src")
    val imgSrc: String,
    @Json(name = "rover")
    val rover: RoverRaw,
    @Json(name = "sol")
    val sol: Int // 1004
)